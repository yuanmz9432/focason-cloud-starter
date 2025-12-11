package com.focason.core.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ProductFetchResponse
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Data
@Builder
public class ProductFetchResponse
{
    /**
     * 行ID
     */
    private Integer id;
    /**
     * 倉庫コード
     */
    private String warehouseCode;
    /**
     * 倉庫名称
     */
    private String warehouseName;
    /**
     * 会社コード
     */
    private String companyCode;
    /**
     * 倉庫ステータス
     */
    private Integer warehouseStatus;
    /**
     * 関連ストアコード配列
     */
    private String[] clientCodes;
}
