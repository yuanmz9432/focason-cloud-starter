/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.platform.file.repository;



import static java.util.stream.Collectors.toList;

import com.focason.core.attribute.FsPagination;
import com.focason.core.attribute.FsResultSet;
import com.focason.core.attribute.FsSort;
import com.focason.core.dao.Io001FileTaskEntityDao;
import com.focason.core.dao.Io002TaskDetailEntityDao;
import com.focason.core.entity.Io001FileTaskEntity;
import com.focason.core.entity.Io002TaskDetailEntity;
import com.focason.platform.file.dao.FileTaskDao;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * FileRepository
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileTaskRepository
{
    private final Io001FileTaskEntityDao io001FileTaskEntityDao;
    private final Io002TaskDetailEntityDao io002TaskDetailEntityDao;
    private final FileTaskDao fileTaskDao;

    public void save(Io001FileTaskEntity entity) {
        io001FileTaskEntityDao.insert(entity);
    }

    public void modify(Io001FileTaskEntity entity) {
        io001FileTaskEntityDao.update(entity);
    }

    public void saveTaskDetail(List<Io002TaskDetailEntity> entities) {
        io002TaskDetailEntityDao.insert(entities);
    }

    public Optional<Io001FileTaskEntity> findImportTask(String taskCode) {
        return fileTaskDao.selectImportTask(taskCode);
    }

    public Optional<Io001FileTaskEntity> findExportTask(String taskCode) {
        return fileTaskDao.selectExportTask(taskCode);
    }

    public void updateImportTask(Io001FileTaskEntity entity) {
        io001FileTaskEntityDao.update(entity);
    }

    public List<Io002TaskDetailEntity> findImportDetails(String taskCode) {
        return fileTaskDao.selectImportDetails(taskCode);
    }

    public void updateImportDetail(List<Io002TaskDetailEntity> entities) {
        io002TaskDetailEntityDao.update(entities);
    }

    public FsResultSet<Io001FileTaskEntity> findAll(Condition condition, FsPagination pagination, Sort sort) {
        var options = pagination.toSelectOptions().count();
        var entities = fileTaskDao.selectAll(condition, options, sort, toList());
        return new FsResultSet<>(entities, options.getCount());
    }

    /**
     * 検索条件
     */
    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @With
    public static class Condition
    {
        /**
         * デフォルトの検索条件
         */
        public static final Condition DEFAULT = new Condition();

        /**
         * 営業単位
         */
        private String businessUnit;

        /**
         * ファイルタイプ
         */
        private List<Integer> fileType;

        /**
         * ファイルステータス
         */
        private List<Integer> fileTaskStatus;

        /**
         * ファイルモジュール
         */
        private List<Integer> fileModule;
    }

    /**
     * ソートパラメータ
     */
    @AllArgsConstructor
    @Value
    public static class Sort
    {

        /**
         * デフォルトの検索条件
         */
        public static final Sort DEFAULT = new Sort(SortColumn.ID, FsSort.Direction.ASC);

        /**
         * ソート列
         */
        SortColumn column;

        /**
         * ソート順序
         */
        FsSort.Direction direction;

        /**
         * このソートパラメータをSQLステートメント形式に変換して返します。
         *
         * @return SQLステートメント
         */
        public String toSql() {
            return column.getColumnName() + " " + direction.name();
        }

        /**
         * {@link FsSort} から新規ソートパラメータを生成します。
         *
         * @param sort {@link FsSort}
         * @return ソートパラメータ
         */
        public static Sort fromFsSort(FsSort sort) {
            return new Sort(SortColumn.fromPropertyName(sort.property()), sort.direction());
        }
    }

    /**
     * ソート列
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum SortColumn
    {

        ID("id", "id");

        /**
         * プロパティ名
         */
        @Getter
        private final String propertyName;

        /**
         * データベース列名
         */
        @Getter
        private final String columnName;

        public static SortColumn fromPropertyName(String propertyName) {
            return Arrays.stream(values())
                .filter(v -> v.propertyName.equals(propertyName))
                .findFirst()
                .orElseThrow(
                    () -> new IllegalArgumentException("propertyName = '" + propertyName + "' is not supported."));
        }
    }
}
