package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ProductOption
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
public class ProductOptionResource extends FsResource
{

    /** 行ID */
    private Integer id;
    /** ストアコード */
    private String clientCode;
    /** 商品SKU */
    private String productSku;
    /** オプションキー */
    private String optionKey;
    /** オプション値 */
    private String optionValue;
}
