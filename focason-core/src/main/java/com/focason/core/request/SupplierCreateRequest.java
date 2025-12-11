package com.focason.core.request;



import lombok.Data;

/**
 * Supplier Create Request
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class SupplierCreateRequest
{
    /**
     * サプライヤー名称
     */
    private String supplierName;

    /**
     * 荷主コード
     */
    private String clientCode;
}
