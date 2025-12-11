package com.focason.core.response;



import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ProductSearchResponse
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Data
@Builder
public class ProductSearchResponse
{
    /**
     * 行ID
     */
    private Integer id;
    /**
     * 商品SKU
     */
    private String productSku;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 荷主コード
     */
    private String clientCode;
    /**
     * 荷主名称
     */
    private String clientName;
    /**
     * 商品タイプ
     */
    private Integer productType;
    /**
     * 税込み単価
     */
    private BigDecimal includedTaxUnitPrice;
    /**
     * 税抜き単価
     */
    private BigDecimal excludedTaxUnitPrice;
    /**
     * 税金
     */
    private BigDecimal tax;
    /**
     * 税率
     */
    private Integer taxRate;
}
