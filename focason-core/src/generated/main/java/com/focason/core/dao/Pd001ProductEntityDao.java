/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.dao;



import com.focason.core.entity.Pd001ProductEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * 商品のDao（※自動生成のため、改修不可！）
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Dao
@ConfigAutowireable
public interface Pd001ProductEntityDao
{

    /**
     * エンティティIDを指定して、データベースからエンティティを一件を取得します。
     *
     * @param id 主キー
     * @return {@link Pd001ProductEntity}
     */
    @Select
    Pd001ProductEntity selectById(int id);

    /**
     * データベースにエンティティを挿入（新規作成）します。
     *
     * @param entity 挿入するエンティティ
     * @return エンティティ挿入結果が返されます。
     */
    @Insert
    int insert(Pd001ProductEntity entity);

    /**
     * データベースのエンティティを更新します。
     *
     * @param entity 更新するエンティティ
     * @return エンティティ更新結果が返されます。
     */
    @Update
    int update(Pd001ProductEntity entity);

    /**
     * エンティティIDを指定して、データベースからエンティティを削除します。（物理削除）
     *
     * @param id エンティティID
     * @return エンティティ削除件数が返されます。
     */
    @Delete(sqlFile = true)
    int deleteById(int id);

    /**
     * エンティティIDを指定して、データベースからエンティティを削除します。（論理削除）
     *
     * @param id エンティティID
     * @return エンティティ削除件数が返されます。
     */
    @Update(sqlFile = true)
    int deleteLogicById(int id);

    /**
     * @param entities エンティティリスト
     * @return エンティティ作成結果が返されます。
     */
    @org.seasar.doma.BatchInsert
    int[] insert(java.util.List<Pd001ProductEntity> entities);

    /**
     * @param entities エンティティリスト
     * @return エンティティ更新結果が返されます。
     */
    @org.seasar.doma.BatchUpdate
    int[] update(java.util.List<Pd001ProductEntity> entities);

    /**
     * @param entities エンティティリスト
     * @return エンティティ削除結果が返されます。
     */
    @org.seasar.doma.BatchDelete
    int[] delete(java.util.List<Pd001ProductEntity> entities);

}
