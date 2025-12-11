package com.focason.core.request;



import lombok.Data;

/**
 * ShelfUnitCreateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ShelfUnitCreateRequest
{
    /** 棚ユニットコード */
    private String shelfUnitCode;
    /** 倉庫コード */
    private String warehouseCode;
    /** ゾーンコード */
    private String zoneCode;
    /** 棚コード */
    private String shelfCode;
    /** 棚ユニット名称 */
    private String shelfUnitName;
    /** 備考 */
    private String remark;
}
