package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserDepartmentResource
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
public class WarehouseUserResource extends FsResource
{
    /** 行ID */
    private Integer id;
    /** 従業員コード:Amazon Cognito採番のUUID */
    private String uuid;
    /** 部門コード */
    private String warehouseCode;
}
