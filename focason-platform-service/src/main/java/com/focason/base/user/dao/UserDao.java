// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.base.user.dao;


import com.focason.base.user.repository.UserRepository;
import com.focason.core.entity.Base001UserEntity;
import com.focason.core.entity.Base003UserTokenEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * UserDao
 * <p>
 * Doma Data Access Object (DAO) interface for querying user-related data (Base001)
 * and user token data (Base003). It is used by the {@link UserRepository} for
 * complex searches and entity lookups.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Dao
@ConfigAutowireable // Doma-boot annotation to make this DAO auto-injectable
public interface UserDao
{

    /**
     * Selects and paginates user entities based on specified conditions and sorting.
     *
     * <p>
     * Uses {@code SelectType.COLLECT} to allow complex result handling (like streaming or list collection)
     * and supports Doma's {@code SelectOptions} for pagination and counting.
     * </p>
     *
     * @param <R> The type of the result (usually List<Base001UserEntity>).
     * @param condition The search condition DTO.
     * @param options The pagination and count options.
     * @param sort The sorting criteria.
     * @param list The collector to materialize the results.
     * @return The collected result list.
     */
    @Select(strategy = SelectType.COLLECT)
    <R> R selectAll(UserRepository.Condition condition, SelectOptions options, UserRepository.Sort sort,
        Collector<Base001UserEntity, ?, R> list);

    /**
     * Selects the UIDs of all currently active users in the system.
     * The criteria for 'active' is typically defined in the associated SQL file.
     *
     * @return A list of active user UIDs.
     */
    @Select
    List<String> selectActiveUids();

    /**
     * Finds a user entity by their unique email address.
     *
     * @param email The email address of the user.
     * @return An Optional containing the {@link Base001UserEntity} if found.
     */
    @Select
    Optional<Base001UserEntity> selectUserByEmail(String email);

    /**
     * Finds a user entity by their unique identifier (UID).
     *
     * @param uid The UID of the user.
     * @return An Optional containing the {@link Base001UserEntity} if found.
     */
    @Select
    Optional<Base001UserEntity> selectUserByUid(String uid);

    /**
     * Finds a user token entity by user ID and device ID. This is typically used for
     * persistent login or session management.
     *
     * @param uid The user ID.
     * @param deviceId The unique ID of the device.
     * @return An Optional containing the {@link Base003UserTokenEntity} if found.
     */
    @Select
    Optional<Base003UserTokenEntity> selectUserToken(String uid, String deviceId);
}
