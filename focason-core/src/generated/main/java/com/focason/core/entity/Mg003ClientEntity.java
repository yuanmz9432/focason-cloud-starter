/* Copyright 2025 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import org.seasar.doma.*;

/**
 * 
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(name = "mg003_client")
public class Mg003ClientEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 会社コード */
    @Column(name = "company_code")
    String companyCode;
    /** 荷主名称 */
    @Column(name = "client_name")
    String clientName;
    /** 荷主ステータス:1:稼働中, 2:非稼働中, 3:閉鎖中 */
    @Column(name = "client_status")
    Integer clientStatus;

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
     * Returns the clientName.
     *
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the clientName.
     *
     * @param clientName the clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Returns the clientStatus.
     *
     * @return the clientStatus
     */
    public Integer getClientStatus() {
        return clientStatus;
    }

    /**
     * Sets the clientStatus.
     *
     * @param clientStatus the clientStatus
     */
    public void setClientStatus(Integer clientStatus) {
        this.clientStatus = clientStatus;
    }
}
