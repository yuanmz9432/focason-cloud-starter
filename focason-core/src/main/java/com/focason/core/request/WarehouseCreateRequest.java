package com.focason.core.request;



import lombok.Data;

/**
 * WarehouseCreateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class WarehouseCreateRequest
{

    /**
     * 倉庫名称
     */
    private String warehouseName;

    /**
     * 会社コード
     */
    private String companyCode;

    /**
     * 倉庫ステータス
     */
    private Integer warehouseStatus;

    /**
     * ストアコード配列
     */
    private String[] clientCodes;
}
