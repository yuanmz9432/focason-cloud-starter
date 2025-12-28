/* Copyright 2025 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * メールログ
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "base006_email_log")
public class Base006EmailLogEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** メールアドレス */
    @Column(name = "email")
    String email;
    /** メール件名 */
    @Column(name = "subject")
    String subject;
    /** メール本文 */
    @Column(name = "content")
    String content;
    /** 送信状態:0: 未送信, 1: 送信成功, 2: 送信失敗 */
    @Column(name = "status")
    Integer status;
    /** テンプレートコード */
    @Column(name = "template_code")
    String templateCode;
    /** エラー内容 */
    @Column(name = "error_message")
    String errorMessage;

    /**
     * Returns the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject.
     *
     * @param subject the subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Returns the templateCode.
     *
     * @return the templateCode
     */
    public String getTemplateCode() {
        return templateCode;
    }

    /**
     * Sets the templateCode.
     *
     * @param templateCode the templateCode
     */
    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    /**
     * Returns the errorMessage.
     *
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the errorMessage.
     *
     * @param errorMessage the errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
