/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 会社
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "mg001_company")
public class Mg001CompanyEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 会社コード */
    @Column(name = "company_code")
    String companyCode;
    /** 会社名称 */
    @Column(name = "company_name")
    String companyName;
    /** 会社ステータス:1:稼働中, 2:非稼働中, 3:閉鎖中 */
    @Column(name = "company_status")
    Integer companyStatus;

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
     * Returns the companyName.
     *
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the companyName.
     *
     * @param companyName the companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Returns the companyStatus.
     *
     * @return the companyStatus
     */
    public Integer getCompanyStatus() {
        return companyStatus;
    }

    /**
     * Sets the companyStatus.
     *
     * @param companyStatus the companyStatus
     */
    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }
}
