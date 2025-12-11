package com.focason.core.request;



import java.util.List;
import lombok.Data;

/**
 * ProductOptionUpdateRequest.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ProductOptionUpdateRequest
{
    /** 商品オプションリスト */
    public List<ProductOption> productOptions;

    @Data
    public static class ProductOption
    {
        /** ストアコード */
        private String clientCode;
        /** オプションキー */
        private String productSku;
        /** オプションキー */
        private String optionKey;
        /** オプション値 */
        private String optionValue;
    }
}
