package com.focason.core.request;



import lombok.Data;

/**
 * StoreUpdateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ClientUpdateRequest
{
    /**
     * ストア名称
     */
    private String clientName;

    /**
     * ストアステータス
     */
    private Integer clientStatus;

    /**
     * 倉庫コード配列
     */
    String[] warehouseCodes;
}
