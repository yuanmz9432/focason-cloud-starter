package com.focason.core.request;



import lombok.Data;

/**
 * Zone Create Request
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ZoneCreateRequest
{
    /**
     * ゾーンコード
     */
    private String zoneCode;
    /**
     * 倉庫コード
     */
    private String warehouseCode;
    /**
     * ゾーン名称
     */
    private String zoneName;
    /**
     * ゾーンタイプ
     */
    private Integer zoneType;
}
