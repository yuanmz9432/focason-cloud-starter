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
@Table(name = "mg008_sponsor")
public class Mg008SponsorEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 依頼主コード */
    @Column(name = "sponsor_code")
    String sponsorCode;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 依頼主名前 */
    @Column(name = "sponsor_name")
    String sponsorName;

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
     * Returns the sponsorCode.
     *
     * @return the sponsorCode
     */
    public String getSponsorCode() {
        return sponsorCode;
    }

    /**
     * Sets the sponsorCode.
     *
     * @param sponsorCode the sponsorCode
     */
    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    /**
     * Returns the clientCode.
     *
     * @return the clientCode
     */
    public String getClientCode() {
        return clientCode;
    }

    /**
     * Sets the clientCode.
     *
     * @param clientCode the clientCode
     */
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    /**
     * Returns the sponsorName.
     *
     * @return the sponsorName
     */
    public String getSponsorName() {
        return sponsorName;
    }

    /**
     * Sets the sponsorName.
     *
     * @param sponsorName the sponsorName
     */
    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
}
