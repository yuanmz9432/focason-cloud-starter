package com.focason.core.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ZoneSearchResponse
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Data
@Builder
public class ZoneSearchResponse
{
    /**
     * 行ID
     */
    private Integer id;
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
