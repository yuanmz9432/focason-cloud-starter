// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.consumer;

import com.focason.core.domain.EmailType;
import com.focason.core.exception.FsSendMailFailedException;
import com.focason.core.resource.EmailResource;
import com.focason.platform.properties.EmailProps;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import io.awspring.cloud.sqs.annotation.SqsListener;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * Email SQS Consumer
 * <p>
 * Consumes messages from AWS SQS email-send-queue and processes them.
 * This consumer polls the queue at regular intervals and processes received messages.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class EmailSqsConsumer
{
    private static final Logger logger = LoggerFactory.getLogger(EmailSqsConsumer.class);
    public static final String QUEUE_NAME = "focason-email-send-queue";

    /** Mail sender provided by Spring Boot autoconfiguration. */
    private final JavaMailSender mailSender;
    /** Freemarker configuration bean, pre-configured to load email templates. */
    private final Configuration freemarkerConfig;
    /** Application properties containing configuration details like send-from address. */
    private final EmailProps emailProps;

    @SqsListener(QUEUE_NAME)
    public void consumeMessage(EmailResource resource) {
        logger.info("Processing email send request for type: {}, to: {}", resource.emailType(), resource.to());

        try {
            // 1. Get the template name based on the email type
            var template = freemarkerConfig.getTemplate(EmailType.of(resource.emailType()).getTemplate());

            // 2. Generate HTML content from the template and payload data
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, resource.payload());

            // 3. Initialize and configure the MimeMessage
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // Use MimeMessageHelper for easy MIME message creation, setting multipart=true
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            // Get sender info from application properties, ensuring consistency
            String sendFrom = emailProps.getSendFrom();
            String sendBy = emailProps.getSendBy();

            helper.setFrom(new InternetAddress(sendFrom, sendBy));
            helper.setTo(resource.to());
            helper.setSubject(EmailType.of(resource.emailType()).getSubject());
            helper.setText(htmlContent, true); // true indicates that the content is HTML

            // 4. Send the email
            mailSender.send(mimeMessage);
            logger.info("Email sent successfully to: {}", resource.to());

        } catch (IOException e) {
            // Handles issues like template not found or encoding errors
            logger.error("Template/IO error during email processing: {}", e.getMessage());
            throw new FsSendMailFailedException(resource.to());
        } catch (TemplateException e) {
            // Handles issues within the Freemarker template processing (e.g., missing variables)
            logger.error("Freemarker template error during email processing: {}", e.getMessage());
            throw new FsSendMailFailedException(resource.to());
        } catch (MessagingException e) {
            // Handles issues during MIME message creation or the final mail sending process
            logger.error("Messaging error during email sending: {}", e.getMessage());
            throw new FsSendMailFailedException(resource.to());
        }
    }
}
