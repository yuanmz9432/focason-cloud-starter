package com.focason.core.queue;



import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ProcessingImportedDataQueue
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Builder(toBuilder = true)
@Data
public class ProcessingImportedDataQueue
{
    /** タスクコード */
    private String taskCode;
}
