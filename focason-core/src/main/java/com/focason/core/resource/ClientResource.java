package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * StoreResource
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class ClientResource extends FsResource
{
    /**
     * 行ID
     */
    private Integer id;
    /**
     * ストアコード
     */
    private String clientCode;
    /**
     * ストア名称
     */
    private String clientName;
    /**
     * 会社コード
     */
    private String companyCode;
    /**
     * ステータス
     */
    private Integer clientStatus;
    /**
     * 関連倉庫コード配列
     */
    private String[] warehouseCodes;
}
