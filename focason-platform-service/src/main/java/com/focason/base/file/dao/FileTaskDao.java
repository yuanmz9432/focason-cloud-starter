package com.focason.base.file.dao;



import com.focason.base.file.repository.FileTaskRepository;
import com.focason.core.entity.Io001FileTaskEntity;
import com.focason.core.entity.Io002TaskDetailEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * FileTaskDao
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Dao
@ConfigAutowireable
public interface FileTaskDao
{

    @Select
    Optional<Io001FileTaskEntity> selectImportTask(String taskCode);

    @Select
    Optional<Io001FileTaskEntity> selectExportTask(String taskCode);

    @Select
    List<Io002TaskDetailEntity> selectImportDetails(String taskCode);

    @Select(strategy = SelectType.COLLECT)
    <R> R selectAll(FileTaskRepository.Condition condition, SelectOptions options, FileTaskRepository.Sort sort,
        Collector<Io001FileTaskEntity, ?, R> list);

}
