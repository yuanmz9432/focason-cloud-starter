/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.entity;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.seasar.doma.*;

/**
 * 受注
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Entity
@Table(catalog = "focason", name = "od001_order")
public class Od001OrderEntity extends FsEntity
{
    /** 行ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /** 受注コード */
    @Column(name = "order_code")
    String orderCode;
    /** 荷主コード */
    @Column(name = "client_code")
    String clientCode;
    /** 受注ソース:1:外部システム, 2:CSVインポート, 3:手動入力 */
    @Column(name = "order_source")
    Integer orderSource;
    /** 外部受注コード */
    @Column(name = "external_order_code")
    String externalOrderCode;
    /** 受注ステータス:1:未処理, 2:出荷依頼中, 3:出荷済み, 4:キャンセル, 5:受注分割, 6:受注統合 */
    @Column(name = "order_status")
    Integer orderStatus;
    /** 倉庫コード */
    @Column(name = "warehouse_code")
    String warehouseCode;
    /** 出荷コード */
    @Column(name = "shipment_code")
    String shipmentCode;
    /** 受注日時 */
    @Column(name = "order_datetime")
    LocalDateTime orderDatetime;
    /** 出荷依頼日時 */
    @Column(name = "shipment_request_datetime")
    LocalDateTime shipmentRequestDatetime;
    /** 配送方法 */
    @Column(name = "delivery_method")
    String deliveryMethod;
    /** 配送希望日 */
    @Column(name = "desired_delivery_date")
    LocalDate desiredDeliveryDate;
    /** 配送希望時間帯 */
    @Column(name = "desired_delivery_time_slot")
    String desiredDeliveryTimeSlot;
    /** 支払方法 */
    @Column(name = "payment_method")
    String paymentMethod;
    /** 総金額 */
    @Column(name = "total_amount")
    BigDecimal totalAmount;
    /** 商品の合計金額 */
    @Column(name = "total_product_amount")
    BigDecimal totalProductAmount;
    /** 税金 */
    @Column(name = "total_tax")
    BigDecimal totalTax;
    /** 送料 */
    @Column(name = "delivery_fee")
    BigDecimal deliveryFee;
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
    @Column(name = "customer_phone")
    String customerPhone;
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
    @Column(name = "shipment_phone")
    String shipmentPhone;
    /** 配送先_メールアドレス */
    @Column(name = "shipment_email")
    String shipmentEmail;
    /** 備考 */
    @Column(name = "remark")
    String remark;

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
     * Returns the orderSource.
     *
     * @return the orderSource
     */
    public Integer getOrderSource() {
        return orderSource;
    }

    /**
     * Sets the orderSource.
     *
     * @param orderSource the orderSource
     */
    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    /**
     * Returns the externalOrderCode.
     *
     * @return the externalOrderCode
     */
    public String getExternalOrderCode() {
        return externalOrderCode;
    }

    /**
     * Sets the externalOrderCode.
     *
     * @param externalOrderCode the externalOrderCode
     */
    public void setExternalOrderCode(String externalOrderCode) {
        this.externalOrderCode = externalOrderCode;
    }

    /**
     * Returns the orderStatus.
     *
     * @return the orderStatus
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the orderStatus.
     *
     * @param orderStatus the orderStatus
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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
     * Returns the orderDatetime.
     *
     * @return the orderDatetime
     */
    public LocalDateTime getOrderDatetime() {
        return orderDatetime;
    }

    /**
     * Sets the orderDatetime.
     *
     * @param orderDatetime the orderDatetime
     */
    public void setOrderDatetime(LocalDateTime orderDatetime) {
        this.orderDatetime = orderDatetime;
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
     * Returns the totalTax.
     *
     * @return the totalTax
     */
    public BigDecimal getTotalTax() {
        return totalTax;
    }

    /**
     * Sets the totalTax.
     *
     * @param totalTax the totalTax
     */
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    /**
     * Returns the deliveryFee.
     *
     * @return the deliveryFee
     */
    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    /**
     * Sets the deliveryFee.
     *
     * @param deliveryFee the deliveryFee
     */
    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
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
     * Returns the customerPhone.
     *
     * @return the customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Sets the customerPhone.
     *
     * @param customerPhone the customerPhone
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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
     * Returns the shipmentPhone.
     *
     * @return the shipmentPhone
     */
    public String getShipmentPhone() {
        return shipmentPhone;
    }

    /**
     * Sets the shipmentPhone.
     *
     * @param shipmentPhone the shipmentPhone
     */
    public void setShipmentPhone(String shipmentPhone) {
        this.shipmentPhone = shipmentPhone;
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
     * Returns the remark.
     *
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the remark.
     *
     * @param remark the remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
