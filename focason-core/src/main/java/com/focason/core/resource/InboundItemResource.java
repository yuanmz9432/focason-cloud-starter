package com.focason.core.resource;



import com.focason.core.annotation.TargetElementType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * InboundResource
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class InboundItemResource extends FsResource
{
    /** 行ID */
    private Integer id;
    /** 入庫部品コード */
    private String inboundItemCode;
    /** 入庫コード */
    private String inboundCode;
    /** 商品コード */
    private String productSku;
    /** 予想数量 */
    private Integer expectedQuantity;
    /** 実際数量 */
    private Integer actualQuantity;
    /** 棚ユニット */
    private String shelfUnitCode;
    /** 入庫詳細ステータス:1:未処理, 2:一部入庫, 3:入庫済み, 4:キャンセル, 5:異常 */
    private Integer inboundItemStatus;
    /** ロット番号 */
    private String lotNumber;
    /** 製造日 */
    private LocalDate productionDate;
    /** 構成品リスト */
    @TargetElementType(InboundShelvingResource.class)
    @Builder.Default
    public List<InboundShelvingResource> inboundShelvingList = new ArrayList<>();
}
