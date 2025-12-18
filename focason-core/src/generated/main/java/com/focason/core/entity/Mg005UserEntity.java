/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "mg005_user")
public class Mg005UserEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** UUID */
    @Column(name = "uuid")
    String uuid;
    /** ユーザー名 */
    @Column(name = "username")
    String username;
    /** メールアドレス */
    @Column(name = "email")
    String email;
    /** パスワード */
    @Column(name = "password")
    String password;
    /** 会社コード */
    @Column(name = "company_code")
    String companyCode;
    /** 電話番号 */
    @Column(name = "phone")
    String phone;
    /** タイプ:1:ルートユーザー, 2:通常ユーザー */
    @Column(name = "type")
    Integer type;
    /** 認証コード */
    @Column(name = "verification_code")
    String verificationCode;
    /** ステータス:1:認証待ち, 2:正常, 3:ブロック */
    @Column(name = "is_verified")
    Integer isVerified;
    /** 画像 */
    @Column(name = "picture")
    String picture;
    /** リフレッシュトークン */
    @Column(name = "refresh_token")
    String refreshToken;

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
     * Returns the uuid.
     *
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the uuid.
     *
     * @param uuid the uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the companyCode.
     *
     * @return the companyCode
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * Sets the companyCode.
     *
     * @param companyCode the companyCode
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * Returns the phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the type.
     *
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type
     */
    public void setType(Integer type) {
        this.type = type;
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
     * Returns the isVerified.
     *
     * @return the isVerified
     */
    public Integer getIsVerified() {
        return isVerified;
    }

    /**
     * Sets the isVerified.
     *
     * @param isVerified the isVerified
     */
    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * Returns the picture.
     *
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets the picture.
     *
     * @param picture the picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
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
}
