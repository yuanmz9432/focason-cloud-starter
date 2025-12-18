/* Copyright 2023 Focason Co.,Ltd. AllRights Reserved. */
package com.focason.core.dao;



import com.focason.core.entity.Sp003ShipmentConsolidationEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * のDao（※自動生成のため、改修不可！）
 *
 * @since 1.0.0
 * @author Focason Lab Team
 */
@Dao
@ConfigAutowireable
public interface Sp003ShipmentConsolidationEntityDao
{

    /**
     * エンティティIDを指定して、データベースからエンティティを一件を取得します。
     *
     * @param id 主キー
     * @return {@link Sp003ShipmentConsolidationEntity}
     */
    @Select
    Sp003ShipmentConsolidationEntity selectById(int id);

    /**
     * データベースにエンティティを挿入（新規作成）します。
     *
     * @param entity 挿入するエンティティ
     * @return エンティティ挿入結果が返されます。
     */
    @Insert
    int insert(Sp003ShipmentConsolidationEntity entity);

    /**
     * データベースのエンティティを更新します。
     *
     * @param entity 更新するエンティティ
     * @return エンティティ更新結果が返されます。
     */
    @Update
    int update(Sp003ShipmentConsolidationEntity entity);

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
    int[] insert(java.util.List<Sp003ShipmentConsolidationEntity> entities);

    /**
     * @param entities エンティティリスト
     * @return エンティティ更新結果が返されます。
     */
    @org.seasar.doma.BatchUpdate
    int[] update(java.util.List<Sp003ShipmentConsolidationEntity> entities);

    /**
     * @param entities エンティティリスト
     * @return エンティティ削除結果が返されます。
     */
    @org.seasar.doma.BatchDelete
    int[] delete(java.util.List<Sp003ShipmentConsolidationEntity> entities);

}
