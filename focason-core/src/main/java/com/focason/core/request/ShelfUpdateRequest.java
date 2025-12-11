package com.focason.core.request;



import java.util.List;
import lombok.Data;

/**
 * ShelvingUpdateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ShelfUpdateRequest
{
    /** 棚名称 */
    private String shelfName;
    /** 備考 */
    private String remark;
    /** 棚ユニットリスト */
    private List<ShelfUnit> shelfUnits;

    @Data
    public static class ShelfUnit
    {
        /** 棚ユニット名称 */
        private String shelfUnitName;
        /** 備考 */
        private String remark;
    }
}
