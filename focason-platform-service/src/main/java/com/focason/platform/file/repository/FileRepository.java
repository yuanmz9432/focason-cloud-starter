/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.platform.file.repository;



import com.focason.core.attribute.FsSort;
import com.focason.core.dao.Base007FileMetadataEntityDao;
import com.focason.core.entity.Base007FileMetadataEntity;
import com.focason.platform.file.dao.FileDao;
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
public class FileRepository
{
    private final Base007FileMetadataEntityDao base007FileMetadataEntityDao;
    private final FileDao fileDao;

    public void save(Base007FileMetadataEntity entity) {
        base007FileMetadataEntityDao.insert(entity);
    }

    public void modify(Base007FileMetadataEntity entity) {
        base007FileMetadataEntityDao.update(entity);
    }

    public Optional<Base007FileMetadataEntity> findFileByObjectKey(String objectKey) {

        return fileDao.selectFileByObjectKey(objectKey);
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
