package com.focason.core.request;



import lombok.Data;

/**
 * ClientCreateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ClientCreateRequest
{
    /**
     * ストア名称
     */
    private String clientName;

    /**
     * 会社コード
     */
    private String companyCode;

    /**
     * ストアステータス
     */
    private Integer clientStatus;

    /**
     * 倉庫コード配列
     */
    private String[] warehouseCodes;
}
