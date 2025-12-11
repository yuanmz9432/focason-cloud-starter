package com.focason.core.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Shelf Fetch Response
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Data
@Builder
public class ShelfFetchResponse
{
    private Integer id;
    private String shelfCode;
    private String warehouseCode;
    private String zoneCode;
    private String shelfName;
    private Integer priority;
    private String remark;
}
