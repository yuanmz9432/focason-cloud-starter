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
@Table(name = "base003_user_token")
public class Base003UserTokenEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** ユーザー識別子 */
    @Column(name = "uid")
    String uid;
    /** アクセストークン */
    @Column(name = "access_token")
    String accessToken;
    /** リフレッシュトークン */
    @Column(name = "refresh_token")
    String refreshToken;
    /** 有効期限 */
    @Column(name = "expires_at")
    LocalDateTime expiresAt;
    /** デバイスID */
    @Column(name = "device_id")
    String deviceId;
    /** デバイスタイプ */
    @Column(name = "device_type")
    Integer deviceType;
    /** ユーザーエージェント */
    @Column(name = "user_agent")
    String userAgent;
    /** IPアドレス */
    @Column(name = "ip_address")
    String ipAddress;

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
     * Returns the accessToken.
     *
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets the accessToken.
     *
     * @param accessToken the accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Returns the refreshToken.
     *
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Sets the refreshToken.
     *
     * @param refreshToken the refreshToken
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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
     * Returns the deviceId.
     *
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the deviceId.
     *
     * @param deviceId the deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Returns the deviceType.
     *
     * @return the deviceType
     */
    public Integer getDeviceType() {
        return deviceType;
    }

    /**
     * Sets the deviceType.
     *
     * @param deviceType the deviceType
     */
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * Returns the userAgent.
     *
     * @return the userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the userAgent.
     *
     * @param userAgent the userAgent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Returns the ipAddress.
     *
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the ipAddress.
     *
     * @param ipAddress the ipAddress
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
