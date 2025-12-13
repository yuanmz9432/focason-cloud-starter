/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.seasar.doma.*;

/**
 * 出荷
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "sp001_shipment")
public class Sp001ShipmentEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 出荷コード */
    @Column(name = "shipment_code")
    String shipmentCode;
    /** 倉庫コード */
    @Column(name = "warehouse_code")
    String warehouseCode;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 受注コード */
    @Column(name = "order_code")
    String orderCode;
    /** 依頼主コード */
    @Column(name = "sponsor_code")
    String sponsorCode;
    /** 出荷ステータス:1:未処理, 2:出荷準備中, 3:引当済み, 4:検品済み, 5:梱包完了, 6:出荷済み, 7:出荷一時停止, 8:出荷分割, 9:出荷統合 */
    @Column(name = "shipment_status")
    Integer shipmentStatus;
    /** 出荷一時停止理由:1:在庫不足, 2:品質問題, 3:配送問題, 4:顧客要求, 5:法的/規制上の問題, 6:出荷統合 */
    @Column(name = "shipment_stop_reason")
    String shipmentStopReason;
    /** 受注受付日時 */
    @Column(name = "order_accept_datetime")
    LocalDateTime orderAcceptDatetime;
    /** 出荷依頼日時 */
    @Column(name = "shipment_request_datetime")
    LocalDateTime shipmentRequestDatetime;
    /** 予定出荷日時 */
    @Column(name = "scheduled_shipment_date")
    LocalDateTime scheduledShipmentDate;
    /** 実際出荷日時 */
    @Column(name = "actual_shipment_date")
    LocalDateTime actualShipmentDate;
    /** 配送希望日 */
    @Column(name = "desired_delivery_date")
    LocalDate desiredDeliveryDate;
    /** 配送希望時間帯 */
    @Column(name = "desired_delivery_time_slot")
    String desiredDeliveryTimeSlot;
    /** 配送方法 */
    @Column(name = "delivery_method")
    String deliveryMethod;
    /** 支払方法 */
    @Column(name = "payment_method")
    String paymentMethod;
    /** 配送伝票番号 */
    @Column(name = "shipment_manifest_number")
    String shipmentManifestNumber;
    /** 配送伝票連携ステータス */
    @Column(name = "shipment_manifest_integration_status")
    Integer shipmentManifestIntegrationStatus;
    /** 合計商品数量 */
    @Column(name = "total_quantity")
    Integer totalQuantity;
    /** 総金額:総金額=商品合計金額+税金+送料+手数料−割引金額 */
    @Column(name = "total_price_amount")
    BigDecimal totalPriceAmount;
    /** 商品合計金額:税抜金額 */
    @Column(name = "total_product_amount")
    BigDecimal totalProductAmount;
    /** 税金 */
    @Column(name = "total_amount")
    BigDecimal totalAmount;
    /** 送料 */
    @Column(name = "shipment_fee")
    BigDecimal shipmentFee;
    /** 手数料 */
    @Column(name = "handling_fee")
    BigDecimal handlingFee;
    /** 割引金額 */
    @Column(name = "discount_amount")
    BigDecimal discountAmount;
    /** 調整金額 */
    @Column(name = "adjustment_amount")
    BigDecimal adjustmentAmount;
    /** 注文者_郵便番号 */
    @Column(name = "customer_postal_code")
    String customerPostalCode;
    /** 注文者_都道府県 */
    @Column(name = "customer_province")
    String customerProvince;
    /** 注文者_市区町村 */
    @Column(name = "customer_city")
    String customerCity;
    /** 注文者_住所1 */
    @Column(name = "customer_address_1")
    String customerAddress1;
    /** 注文者_住所2 */
    @Column(name = "customer_address_2")
    String customerAddress2;
    /** 注文者_名前 */
    @Column(name = "customer_name")
    String customerName;
    /** 注文者_電話番号 */
    @Column(name = "customer_phone_number")
    String customerPhoneNumber;
    /** 注文者_メールアドレス */
    @Column(name = "customer_email")
    String customerEmail;
    /** 配送先_郵便番号 */
    @Column(name = "shipment_postal_code")
    String shipmentPostalCode;
    /** 配送先_都道府県 */
    @Column(name = "shipment_province")
    String shipmentProvince;
    /** 配送先_市区町村 */
    @Column(name = "shipment_city")
    String shipmentCity;
    /** 配送先_住所1 */
    @Column(name = "shipment_address_1")
    String shipmentAddress1;
    /** 配送先_住所2 */
    @Column(name = "shipment_address_2")
    String shipmentAddress2;
    /** 配送先_名前 */
    @Column(name = "shipment_name")
    String shipmentName;
    /** 配送先_電話番号 */
    @Column(name = "shipment_phone_number")
    String shipmentPhoneNumber;
    /** 配送先_メールアドレス */
    @Column(name = "shipment_email")
    String shipmentEmail;
    /** 出荷特記事項 */
    @Column(name = "shipment_remark")
    String shipmentRemark;
    /** 配送伝票特記事項 */
    @Column(name = "shipment_manifest_remark")
    String shipmentManifestRemark;
    /** 納品書特記事項 */
    @Column(name = "delivery_note_remark")
    String deliveryNoteRemark;
    /** 添付ファイル1:Amazon S3パス */
    @Column(name = "attached_file_1")
    String attachedFile1;
    /** 添付ファイル2:Amazon S3パス */
    @Column(name = "attached_file_2")
    String attachedFile2;
    /** 添付ファイル3:Amazon S3パス */
    @Column(name = "attached_file_3")
    String attachedFile3;

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
     * Returns the shipmentCode.
     *
     * @return the shipmentCode
     */
    public String getShipmentCode() {
        return shipmentCode;
    }

    /**
     * Sets the shipmentCode.
     *
     * @param shipmentCode the shipmentCode
     */
    public void setShipmentCode(String shipmentCode) {
        this.shipmentCode = shipmentCode;
    }

    /**
     * Returns the warehouseCode.
     *
     * @return the warehouseCode
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * Sets the warehouseCode.
     *
     * @param warehouseCode the warehouseCode
     */
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
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
     * Returns the orderCode.
     *
     * @return the orderCode
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * Sets the orderCode.
     *
     * @param orderCode the orderCode
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
     * Returns the shipmentStatus.
     *
     * @return the shipmentStatus
     */
    public Integer getShipmentStatus() {
        return shipmentStatus;
    }

    /**
     * Sets the shipmentStatus.
     *
     * @param shipmentStatus the shipmentStatus
     */
    public void setShipmentStatus(Integer shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    /**
     * Returns the shipmentStopReason.
     *
     * @return the shipmentStopReason
     */
    public String getShipmentStopReason() {
        return shipmentStopReason;
    }

    /**
     * Sets the shipmentStopReason.
     *
     * @param shipmentStopReason the shipmentStopReason
     */
    public void setShipmentStopReason(String shipmentStopReason) {
        this.shipmentStopReason = shipmentStopReason;
    }

    /**
     * Returns the orderAcceptDatetime.
     *
     * @return the orderAcceptDatetime
     */
    public LocalDateTime getOrderAcceptDatetime() {
        return orderAcceptDatetime;
    }

    /**
     * Sets the orderAcceptDatetime.
     *
     * @param orderAcceptDatetime the orderAcceptDatetime
     */
    public void setOrderAcceptDatetime(LocalDateTime orderAcceptDatetime) {
        this.orderAcceptDatetime = orderAcceptDatetime;
    }

    /**
     * Returns the shipmentRequestDatetime.
     *
     * @return the shipmentRequestDatetime
     */
    public LocalDateTime getShipmentRequestDatetime() {
        return shipmentRequestDatetime;
    }

    /**
     * Sets the shipmentRequestDatetime.
     *
     * @param shipmentRequestDatetime the shipmentRequestDatetime
     */
    public void setShipmentRequestDatetime(LocalDateTime shipmentRequestDatetime) {
        this.shipmentRequestDatetime = shipmentRequestDatetime;
    }

    /**
     * Returns the scheduledShipmentDate.
     *
     * @return the scheduledShipmentDate
     */
    public LocalDateTime getScheduledShipmentDate() {
        return scheduledShipmentDate;
    }

    /**
     * Sets the scheduledShipmentDate.
     *
     * @param scheduledShipmentDate the scheduledShipmentDate
     */
    public void setScheduledShipmentDate(LocalDateTime scheduledShipmentDate) {
        this.scheduledShipmentDate = scheduledShipmentDate;
    }

    /**
     * Returns the actualShipmentDate.
     *
     * @return the actualShipmentDate
     */
    public LocalDateTime getActualShipmentDate() {
        return actualShipmentDate;
    }

    /**
     * Sets the actualShipmentDate.
     *
     * @param actualShipmentDate the actualShipmentDate
     */
    public void setActualShipmentDate(LocalDateTime actualShipmentDate) {
        this.actualShipmentDate = actualShipmentDate;
    }

    /**
     * Returns the desiredDeliveryDate.
     *
     * @return the desiredDeliveryDate
     */
    public LocalDate getDesiredDeliveryDate() {
        return desiredDeliveryDate;
    }

    /**
     * Sets the desiredDeliveryDate.
     *
     * @param desiredDeliveryDate the desiredDeliveryDate
     */
    public void setDesiredDeliveryDate(LocalDate desiredDeliveryDate) {
        this.desiredDeliveryDate = desiredDeliveryDate;
    }

    /**
     * Returns the desiredDeliveryTimeSlot.
     *
     * @return the desiredDeliveryTimeSlot
     */
    public String getDesiredDeliveryTimeSlot() {
        return desiredDeliveryTimeSlot;
    }

    /**
     * Sets the desiredDeliveryTimeSlot.
     *
     * @param desiredDeliveryTimeSlot the desiredDeliveryTimeSlot
     */
    public void setDesiredDeliveryTimeSlot(String desiredDeliveryTimeSlot) {
        this.desiredDeliveryTimeSlot = desiredDeliveryTimeSlot;
    }

    /**
     * Returns the deliveryMethod.
     *
     * @return the deliveryMethod
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * Sets the deliveryMethod.
     *
     * @param deliveryMethod the deliveryMethod
     */
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    /**
     * Returns the paymentMethod.
     *
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the paymentMethod.
     *
     * @param paymentMethod the paymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Returns the shipmentManifestNumber.
     *
     * @return the shipmentManifestNumber
     */
    public String getShipmentManifestNumber() {
        return shipmentManifestNumber;
    }

    /**
     * Sets the shipmentManifestNumber.
     *
     * @param shipmentManifestNumber the shipmentManifestNumber
     */
    public void setShipmentManifestNumber(String shipmentManifestNumber) {
        this.shipmentManifestNumber = shipmentManifestNumber;
    }

    /**
     * Returns the shipmentManifestIntegrationStatus.
     *
     * @return the shipmentManifestIntegrationStatus
     */
    public Integer getShipmentManifestIntegrationStatus() {
        return shipmentManifestIntegrationStatus;
    }

    /**
     * Sets the shipmentManifestIntegrationStatus.
     *
     * @param shipmentManifestIntegrationStatus the shipmentManifestIntegrationStatus
     */
    public void setShipmentManifestIntegrationStatus(Integer shipmentManifestIntegrationStatus) {
        this.shipmentManifestIntegrationStatus = shipmentManifestIntegrationStatus;
    }

    /**
     * Returns the totalQuantity.
     *
     * @return the totalQuantity
     */
    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * Sets the totalQuantity.
     *
     * @param totalQuantity the totalQuantity
     */
    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    /**
     * Returns the totalPriceAmount.
     *
     * @return the totalPriceAmount
     */
    public BigDecimal getTotalPriceAmount() {
        return totalPriceAmount;
    }

    /**
     * Sets the totalPriceAmount.
     *
     * @param totalPriceAmount the totalPriceAmount
     */
    public void setTotalPriceAmount(BigDecimal totalPriceAmount) {
        this.totalPriceAmount = totalPriceAmount;
    }

    /**
     * Returns the totalProductAmount.
     *
     * @return the totalProductAmount
     */
    public BigDecimal getTotalProductAmount() {
        return totalProductAmount;
    }

    /**
     * Sets the totalProductAmount.
     *
     * @param totalProductAmount the totalProductAmount
     */
    public void setTotalProductAmount(BigDecimal totalProductAmount) {
        this.totalProductAmount = totalProductAmount;
    }

    /**
     * Returns the totalAmount.
     *
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the totalAmount.
     *
     * @param totalAmount the totalAmount
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Returns the shipmentFee.
     *
     * @return the shipmentFee
     */
    public BigDecimal getShipmentFee() {
        return shipmentFee;
    }

    /**
     * Sets the shipmentFee.
     *
     * @param shipmentFee the shipmentFee
     */
    public void setShipmentFee(BigDecimal shipmentFee) {
        this.shipmentFee = shipmentFee;
    }

    /**
     * Returns the handlingFee.
     *
     * @return the handlingFee
     */
    public BigDecimal getHandlingFee() {
        return handlingFee;
    }

    /**
     * Sets the handlingFee.
     *
     * @param handlingFee the handlingFee
     */
    public void setHandlingFee(BigDecimal handlingFee) {
        this.handlingFee = handlingFee;
    }

    /**
     * Returns the discountAmount.
     *
     * @return the discountAmount
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the discountAmount.
     *
     * @param discountAmount the discountAmount
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Returns the adjustmentAmount.
     *
     * @return the adjustmentAmount
     */
    public BigDecimal getAdjustmentAmount() {
        return adjustmentAmount;
    }

    /**
     * Sets the adjustmentAmount.
     *
     * @param adjustmentAmount the adjustmentAmount
     */
    public void setAdjustmentAmount(BigDecimal adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    /**
     * Returns the customerPostalCode.
     *
     * @return the customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * Sets the customerPostalCode.
     *
     * @param customerPostalCode the customerPostalCode
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Returns the customerProvince.
     *
     * @return the customerProvince
     */
    public String getCustomerProvince() {
        return customerProvince;
    }

    /**
     * Sets the customerProvince.
     *
     * @param customerProvince the customerProvince
     */
    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    /**
     * Returns the customerCity.
     *
     * @return the customerCity
     */
    public String getCustomerCity() {
        return customerCity;
    }

    /**
     * Sets the customerCity.
     *
     * @param customerCity the customerCity
     */
    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    /**
     * Returns the customerAddress1.
     *
     * @return the customerAddress1
     */
    public String getCustomerAddress1() {
        return customerAddress1;
    }

    /**
     * Sets the customerAddress1.
     *
     * @param customerAddress1 the customerAddress1
     */
    public void setCustomerAddress1(String customerAddress1) {
        this.customerAddress1 = customerAddress1;
    }

    /**
     * Returns the customerAddress2.
     *
     * @return the customerAddress2
     */
    public String getCustomerAddress2() {
        return customerAddress2;
    }

    /**
     * Sets the customerAddress2.
     *
     * @param customerAddress2 the customerAddress2
     */
    public void setCustomerAddress2(String customerAddress2) {
        this.customerAddress2 = customerAddress2;
    }

    /**
     * Returns the customerName.
     *
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customerName.
     *
     * @param customerName the customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Returns the customerPhoneNumber.
     *
     * @return the customerPhoneNumber
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * Sets the customerPhoneNumber.
     *
     * @param customerPhoneNumber the customerPhoneNumber
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     * Returns the customerEmail.
     *
     * @return the customerEmail
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Sets the customerEmail.
     *
     * @param customerEmail the customerEmail
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * Returns the shipmentPostalCode.
     *
     * @return the shipmentPostalCode
     */
    public String getShipmentPostalCode() {
        return shipmentPostalCode;
    }

    /**
     * Sets the shipmentPostalCode.
     *
     * @param shipmentPostalCode the shipmentPostalCode
     */
    public void setShipmentPostalCode(String shipmentPostalCode) {
        this.shipmentPostalCode = shipmentPostalCode;
    }

    /**
     * Returns the shipmentProvince.
     *
     * @return the shipmentProvince
     */
    public String getShipmentProvince() {
        return shipmentProvince;
    }

    /**
     * Sets the shipmentProvince.
     *
     * @param shipmentProvince the shipmentProvince
     */
    public void setShipmentProvince(String shipmentProvince) {
        this.shipmentProvince = shipmentProvince;
    }

    /**
     * Returns the shipmentCity.
     *
     * @return the shipmentCity
     */
    public String getShipmentCity() {
        return shipmentCity;
    }

    /**
     * Sets the shipmentCity.
     *
     * @param shipmentCity the shipmentCity
     */
    public void setShipmentCity(String shipmentCity) {
        this.shipmentCity = shipmentCity;
    }

    /**
     * Returns the shipmentAddress1.
     *
     * @return the shipmentAddress1
     */
    public String getShipmentAddress1() {
        return shipmentAddress1;
    }

    /**
     * Sets the shipmentAddress1.
     *
     * @param shipmentAddress1 the shipmentAddress1
     */
    public void setShipmentAddress1(String shipmentAddress1) {
        this.shipmentAddress1 = shipmentAddress1;
    }

    /**
     * Returns the shipmentAddress2.
     *
     * @return the shipmentAddress2
     */
    public String getShipmentAddress2() {
        return shipmentAddress2;
    }

    /**
     * Sets the shipmentAddress2.
     *
     * @param shipmentAddress2 the shipmentAddress2
     */
    public void setShipmentAddress2(String shipmentAddress2) {
        this.shipmentAddress2 = shipmentAddress2;
    }

    /**
     * Returns the shipmentName.
     *
     * @return the shipmentName
     */
    public String getShipmentName() {
        return shipmentName;
    }

    /**
     * Sets the shipmentName.
     *
     * @param shipmentName the shipmentName
     */
    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    /**
     * Returns the shipmentPhoneNumber.
     *
     * @return the shipmentPhoneNumber
     */
    public String getShipmentPhoneNumber() {
        return shipmentPhoneNumber;
    }

    /**
     * Sets the shipmentPhoneNumber.
     *
     * @param shipmentPhoneNumber the shipmentPhoneNumber
     */
    public void setShipmentPhoneNumber(String shipmentPhoneNumber) {
        this.shipmentPhoneNumber = shipmentPhoneNumber;
    }

    /**
     * Returns the shipmentEmail.
     *
     * @return the shipmentEmail
     */
    public String getShipmentEmail() {
        return shipmentEmail;
    }

    /**
     * Sets the shipmentEmail.
     *
     * @param shipmentEmail the shipmentEmail
     */
    public void setShipmentEmail(String shipmentEmail) {
        this.shipmentEmail = shipmentEmail;
    }

    /**
     * Returns the shipmentRemark.
     *
     * @return the shipmentRemark
     */
    public String getShipmentRemark() {
        return shipmentRemark;
    }

    /**
     * Sets the shipmentRemark.
     *
     * @param shipmentRemark the shipmentRemark
     */
    public void setShipmentRemark(String shipmentRemark) {
        this.shipmentRemark = shipmentRemark;
    }

    /**
     * Returns the shipmentManifestRemark.
     *
     * @return the shipmentManifestRemark
     */
    public String getShipmentManifestRemark() {
        return shipmentManifestRemark;
    }

    /**
     * Sets the shipmentManifestRemark.
     *
     * @param shipmentManifestRemark the shipmentManifestRemark
     */
    public void setShipmentManifestRemark(String shipmentManifestRemark) {
        this.shipmentManifestRemark = shipmentManifestRemark;
    }

    /**
     * Returns the deliveryNoteRemark.
     *
     * @return the deliveryNoteRemark
     */
    public String getDeliveryNoteRemark() {
        return deliveryNoteRemark;
    }

    /**
     * Sets the deliveryNoteRemark.
     *
     * @param deliveryNoteRemark the deliveryNoteRemark
     */
    public void setDeliveryNoteRemark(String deliveryNoteRemark) {
        this.deliveryNoteRemark = deliveryNoteRemark;
    }

    /**
     * Returns the attachedFile1.
     *
     * @return the attachedFile1
     */
    public String getAttachedFile1() {
        return attachedFile1;
    }

    /**
     * Sets the attachedFile1.
     *
     * @param attachedFile1 the attachedFile1
     */
    public void setAttachedFile1(String attachedFile1) {
        this.attachedFile1 = attachedFile1;
    }

    /**
     * Returns the attachedFile2.
     *
     * @return the attachedFile2
     */
    public String getAttachedFile2() {
        return attachedFile2;
    }

    /**
     * Sets the attachedFile2.
     *
     * @param attachedFile2 the attachedFile2
     */
    public void setAttachedFile2(String attachedFile2) {
        this.attachedFile2 = attachedFile2;
    }

    /**
     * Returns the attachedFile3.
     *
     * @return the attachedFile3
     */
    public String getAttachedFile3() {
        return attachedFile3;
    }

    /**
     * Sets the attachedFile3.
     *
     * @param attachedFile3 the attachedFile3
     */
    public void setAttachedFile3(String attachedFile3) {
        this.attachedFile3 = attachedFile3;
    }
}
