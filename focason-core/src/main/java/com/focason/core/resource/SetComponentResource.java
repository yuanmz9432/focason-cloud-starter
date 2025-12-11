package com.focason.core.resource;



import com.focason.core.entity.Pd002SetComponentEntity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class SetComponentResource extends FsResource
{
    /** 行ID */
    Integer id;
    /** ストアコード */
    String clientCode;
    /** 親商品コード */
    String parentProductSku;
    /** 子商品コード */
    String childProductSku;
    /** 数量 */
    Integer quantity;

    public SetComponentResource(Pd002SetComponentEntity entity) {
        this.id = entity.getId();
        this.parentProductSku = entity.getParentProductSku();
        this.childProductSku = entity.getChildProductSku();
        this.quantity = entity.getQuantity();
        this.setCreatedAt(entity.getCreatedAt());
        this.setCreatedBy(entity.getCreatedBy());
        this.setModifiedAt(entity.getModifiedAt());
        this.setModifiedBy(entity.getModifiedBy());
        this.setIsDeleted(entity.getIsDeleted());
    }
}
