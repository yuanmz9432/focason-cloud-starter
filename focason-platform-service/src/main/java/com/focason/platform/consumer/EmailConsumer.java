// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.consumer;

import com.focason.core.config.EmailQueueConfig;
import com.focason.core.domain.EmailType;
import com.focason.core.exception.FsSendMailFailedException;
import com.focason.core.properties.FsProperties;
import com.focason.core.resource.EmailResource;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * EmailConsumer
 * <p>
 * Message consumer that listens to the email send queue and handles the process
 * of generating email content using Freemarker templates and sending the email
 * via {@link JavaMailSender}.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmailConsumer
{
    final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    /** Mail sender provided by Spring Boot autoconfiguration. */
    private final JavaMailSender mailSender;

    /** Freemarker configuration bean, pre-configured to load email templates. */
    private final Configuration freemarkerConfig;

    /** Application properties containing configuration details like send-from address. */
    private final FsProperties properties;

    /**
     * Consumes messages from the dedicated email sending queue.
     *
     * @param resource The email task resource containing recipient, email type, and template payload.
     * @throws FsSendMailFailedException if message initialization, template processing, or sending fails.
     */
    @RabbitListener(queues = EmailQueueConfig.EMAIL_SEND_QUEUE)
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
            String sendFrom = properties.getEmail().get("send-from");
            String sendBy = properties.getEmail().get("send-by");

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
