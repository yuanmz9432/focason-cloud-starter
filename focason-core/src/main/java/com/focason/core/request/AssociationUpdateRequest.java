package com.focason.core.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AssociationUpdateRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AssociationUpdateRequest
{
    private String warehouseCode;
    private String[] clientCodes;
    private String clientCode;
    private String[] warehouseCodes;
}
