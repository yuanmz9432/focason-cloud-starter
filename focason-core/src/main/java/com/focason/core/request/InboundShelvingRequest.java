package com.focason.core.request;



import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * InboundUpdateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class InboundShelvingRequest
{
    /** 入庫部品コード */
    private String inboundItemCode;
    /** 入庫コード */
    private String inboundCode;
    /** 実際数量 */
    private Integer actualQuantity;
    /** 入庫部品ステータス:1:未処理, 2:一部入庫, 3:入庫済み, 4:キャンセル, 5:異常 */
    private Integer inboundItemStatus;
    /** 入庫棚上げリスト */
    public List<InboundShelving> inboundShelvingList = new ArrayList<>();

    @Data
    public static class InboundShelving
    {
        /** 棚ユニットコード */
        String shelfUnitCode;
        /** 数量 */
        Integer quantity;
    }
}
