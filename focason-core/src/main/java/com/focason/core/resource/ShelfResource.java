package com.focason.core.resource;



import com.focason.core.annotation.TargetElementType;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ShelvingResource
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
public class ShelfResource extends FsResource
{
    /** 行ID */
    private Integer id;
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
    /** 備考 */
    private String remark;
    /** 棚ユニットリスト */
    @TargetElementType(ShelfUnitResource.class)
    @Builder.Default
    public List<ShelfUnitResource> shelfUnits = new ArrayList<>();
}
