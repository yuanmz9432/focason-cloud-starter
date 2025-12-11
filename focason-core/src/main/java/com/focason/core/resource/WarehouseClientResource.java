package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * WarehouseClient Resource
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class WarehouseClientResource
{
    private String warehouseCode;
    @Builder.Default
    private String[] clientCodes = new String[0];
    private String clientCode;
    @Builder.Default
    private String[] warehouseCodes = new String[0];
}
