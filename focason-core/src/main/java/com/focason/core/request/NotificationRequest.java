package com.focason.core.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NotificationRequest
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationRequest
{
    private String[] targets;
    private String title;
    private String content;
    private Integer status;
    private Integer type;
}
