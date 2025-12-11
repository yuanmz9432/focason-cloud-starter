package com.focason.core.request;



import lombok.Data;

/**
 * WarehouseUpdateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class WarehouseUpdateRequest
{

    /**
     * 倉庫名称
     */
    private String warehouseName;
    /**
     * 倉庫ステータス
     */
    private Integer warehouseStatus;
    /**
     * ストアコード配列
     */
    private String[] clientCodes;
}
