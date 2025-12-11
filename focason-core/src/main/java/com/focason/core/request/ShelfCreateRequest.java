package com.focason.core.request;



import java.util.List;
import lombok.Data;

/**
 * ShelvingCreateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ShelfCreateRequest
{
    /** 倉庫コード */
    private String warehouseCode;
    /** ゾーンコード */
    private String zoneCode;
    /** 棚コード */
    private String shelfCode;
    /** 棚名称 */
    private String shelfName;
    /** 優先度 */
    private Integer priority;
    /** 棚ユニットリスト */
    private List<ShelfUnit> shelfUnits;
    /** 備考 */
    private String remark;

    @Data
    public static class ShelfUnit
    {
        /** 棚ユニット名称 */
        private String shelfUnitName;
        /** 備考 */
        private String remark;
    }
}
