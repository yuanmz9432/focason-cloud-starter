package com.focason.core.resource;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * InboundShelvingResource
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
public class InboundShelvingResource extends FsResource
{
    /** 行ID */
    private Integer id;
    /** 入庫棚上げコード */
    private String inboundShelvingCode;
    /** 入庫部品コード */
    private String inboundItemCode;
    /** 棚ユニットコード */
    private String shelfUnitCode;
    /** 数量 */
    private Integer quantity;
}
