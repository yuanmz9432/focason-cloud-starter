package com.focason.core.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FileUploadResponse
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Data
@Builder
public class FileUploadResponse
{
    /** ファイルID */
    private String fileId;
    /** オブジェクトキー */
    private String objectKey;
    /** 署名付きURL */
    private String presignedUrl;
}
