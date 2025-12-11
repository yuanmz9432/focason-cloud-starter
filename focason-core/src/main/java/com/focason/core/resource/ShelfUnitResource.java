package com.focason.core.resource;



import com.focason.core.annotation.TargetElementType;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ShelUnitResource
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
public class ShelfUnitResource extends FsResource
{
    /** 行ID */
    private Integer id;
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
    /** 在庫リスト */
    @TargetElementType(InventoryResource.class)
    @Builder.Default
    public List<InventoryResource> inventories = new ArrayList<>();
}
