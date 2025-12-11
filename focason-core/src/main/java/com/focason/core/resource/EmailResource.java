package com.focason.core.resource;



import java.util.Map;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SendEmailQueue
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
public class EmailResource
{
    /** メールアドレス */
    private String to;
    /** メールタイプ */
    private Integer emailType;
    /** ペイロード */
    private Map<String, Object> payload;
}
