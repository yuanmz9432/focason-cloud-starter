package com.focason.core.resource;



import com.focason.core.annotation.TargetElementType;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Zone Resource
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
public class ZoneResource extends FsResource
{
    /** 行ID */
    private Integer id;
    /** ゾーンコード */
    private String zoneCode;
    /** 倉庫コード */
    private String warehouseCode;
    /** ゾーン名称 */
    private String zoneName;
    /** ゾーンタイプ */
    private Integer zoneType;
    /** 備考 */
    private String remark;
    /** 棚リスト */
    @TargetElementType(ShelfResource.class)
    @Builder.Default
    public List<ShelfResource> shelves = new ArrayList<>();
}
