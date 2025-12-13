/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.time.LocalDate;
import org.seasar.doma.*;

/**
 * 契約
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "mg013_contract")
public class Mg013ContractEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 契約コード */
    @Column(name = "contract_code")
    String contractCode;
    /** 契約種類:1:倉庫契約, 2:ストア契約 */
    @Column(name = "contract_type")
    Integer contractType;
    /** 契約プラン:1:スタンダード, 2:プレミアム */
    @Column(name = "contract_plan")
    Integer contractPlan;
    /** 契約開始日 */
    @Column(name = "contract_start_date")
    LocalDate contractStartDate;
    /** 契約終了日 */
    @Column(name = "contract_end_date")
    LocalDate contractEndDate;
    /** 契約先コード */
    @Column(name = "contractor_code")
    String contractorCode;
    /** 担当者_名前 */
    @Column(name = "manager_name")
    String managerName;
    /** 担当者_電話番号 */
    @Column(name = "manager_phone")
    String managerPhone;
    /** 担当者_メールアドレス */
    @Column(name = "manager_email")
    String managerEmail;

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
     * Returns the contractCode.
     *
     * @return the contractCode
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * Sets the contractCode.
     *
     * @param contractCode the contractCode
     */
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * Returns the contractType.
     *
     * @return the contractType
     */
    public Integer getContractType() {
        return contractType;
    }

    /**
     * Sets the contractType.
     *
     * @param contractType the contractType
     */
    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    /**
     * Returns the contractPlan.
     *
     * @return the contractPlan
     */
    public Integer getContractPlan() {
        return contractPlan;
    }

    /**
     * Sets the contractPlan.
     *
     * @param contractPlan the contractPlan
     */
    public void setContractPlan(Integer contractPlan) {
        this.contractPlan = contractPlan;
    }

    /**
     * Returns the contractStartDate.
     *
     * @return the contractStartDate
     */
    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    /**
     * Sets the contractStartDate.
     *
     * @param contractStartDate the contractStartDate
     */
    public void setContractStartDate(LocalDate contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    /**
     * Returns the contractEndDate.
     *
     * @return the contractEndDate
     */
    public LocalDate getContractEndDate() {
        return contractEndDate;
    }

    /**
     * Sets the contractEndDate.
     *
     * @param contractEndDate the contractEndDate
     */
    public void setContractEndDate(LocalDate contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    /**
     * Returns the contractorCode.
     *
     * @return the contractorCode
     */
    public String getContractorCode() {
        return contractorCode;
    }

    /**
     * Sets the contractorCode.
     *
     * @param contractorCode the contractorCode
     */
    public void setContractorCode(String contractorCode) {
        this.contractorCode = contractorCode;
    }

    /**
     * Returns the managerName.
     *
     * @return the managerName
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * Sets the managerName.
     *
     * @param managerName the managerName
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * Returns the managerPhone.
     *
     * @return the managerPhone
     */
    public String getManagerPhone() {
        return managerPhone;
    }

    /**
     * Sets the managerPhone.
     *
     * @param managerPhone the managerPhone
     */
    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    /**
     * Returns the managerEmail.
     *
     * @return the managerEmail
     */
    public String getManagerEmail() {
        return managerEmail;
    }

    /**
     * Sets the managerEmail.
     *
     * @param managerEmail the managerEmail
     */
    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }
}
