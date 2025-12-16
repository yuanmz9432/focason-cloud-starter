package com.focason.platform.file.dao;


import com.focason.core.entity.Base007FileMetadataEntity;
import java.util.Optional;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * FileDao
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Dao
@ConfigAutowireable
public interface FileDao
{
    @Select
    Optional<Base007FileMetadataEntity> selectFileByObjectKey(String objectKey);
}
