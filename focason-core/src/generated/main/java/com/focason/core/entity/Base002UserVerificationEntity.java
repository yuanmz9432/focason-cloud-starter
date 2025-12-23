/* Copyright 2025 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDateTime;
import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "base002_user_verification")
public class Base002UserVerificationEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** ユーザー識別子 */
    @Column(name = "uid")
    String uid;
    /** 認証コード */
    @Column(name = "verification_code")
    String verificationCode;
    /** 認証期限 */
    @Column(name = "expires_at")
    LocalDateTime expiresAt;
    /** 認証日時 */
    @Column(name = "verified_at")
    LocalDateTime verifiedAt;

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
     * Returns the uid.
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the uid.
     *
     * @param uid the uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Returns the verificationCode.
     *
     * @return the verificationCode
     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * Sets the verificationCode.
     *
     * @param verificationCode the verificationCode
     */
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    /**
     * Returns the expiresAt.
     *
     * @return the expiresAt
     */
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    /**
     * Sets the expiresAt.
     *
     * @param expiresAt the expiresAt
     */
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * Returns the verifiedAt.
     *
     * @return the verifiedAt
     */
    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    /**
     * Sets the verifiedAt.
     *
     * @param verifiedAt the verifiedAt
     */
    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}
