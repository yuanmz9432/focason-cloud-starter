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
public class InboundResource extends FsResource
{
    /** 行ID */
    private Integer id;
    /** 入庫コード */
    private String inboundCode;
    /** 倉庫コード */
    private String warehouseCode;
    /** ストアコード */
    private String clientCode;
    /** サプライヤーコード */
    private String supplierCode;
    /** 入庫依頼日時 */
    private LocalDate requestInboundDate;
    /** 予想入庫日時 */
    private LocalDate expectedInboundDate;
    /** 実際入庫日時 */
    private LocalDate actualInboundDate;
    /** 入庫ステータス:1:未処理, 2:一部入庫, 3:入庫済み, 4:キャンセル, 5:異常 */
    private Integer inboundStatus;
    /** 入庫タイプ:1:通常入庫, 2:返品入庫, 3:調達入庫, 4:棚卸差異調整入庫 */
    private Integer inboundType;
    /** 配送伝票番号 */
    private String shipmentManifestNumber;
    /** 店舗側担当者 */
    private String clientManager;
    /** 倉庫側担当者 */
    private String warehouseManager;
    /** 備考 */
    private String remark;
    /** 入庫部品リスト */
    @TargetElementType(InboundItemResource.class)
    @Builder.Default
    public List<InboundItemResource> inboundItems = new ArrayList<>();
}
