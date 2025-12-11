package com.focason.core.request;



import java.util.List;
import lombok.Data;

/**
 * SetComponentUpdateRequest.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class SetComponentUpdateRequest
{
    /** 商品明細リスト */
    public List<SetComponent> setComponents;

    @Data
    public static class SetComponent
    {
        /** ストアコード */
        private String clientCode;
        /** 親商品コード */
        private String parentProductCode;
        /** 子商品コード */
        private String childProductCode;
        /** 数量 */
        private Integer quantity;
    }
}
