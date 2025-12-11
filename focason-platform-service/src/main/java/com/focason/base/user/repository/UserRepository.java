// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.base.user.repository;


import static java.util.stream.Collectors.toList;

import com.focason.base.user.dao.UserDao;
import com.focason.core.attribute.FsPagination;
import com.focason.core.attribute.FsResultSet;
import com.focason.core.attribute.FsSort;
import com.focason.core.dao.Base001UserEntityDao;
import com.focason.core.dao.Base003UserTokenEntityDao;
import com.focason.core.entity.Base001UserEntity;
import com.focason.core.entity.Base003UserTokenEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 * <p>
 * Repository layer responsible for managing user-related data access, including
 * user profiles and user authentication tokens. It acts as an abstraction over
 * various DAO interfaces.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepository
{

    /** Custom DAO for complex user queries (e.g., joins, active UIDs). */
    private final UserDao userDao;
    /** Generic DAO for the main user entity (Base001). */
    private final Base001UserEntityDao base001UserEntityDao;
    /** Generic DAO for the user token entity (Base003). */
    private final Base003UserTokenEntityDao base003UserTokenEntityDao;

    /**
     * Finds and paginates user entities based on complex criteria.
     *
     * @param condition The search condition DTO.
     * @param pagination The pagination settings.
     * @param sort The sorting criteria.
     * @return A result set containing paginated {@link Base001UserEntity} and the total count.
     */
    public FsResultSet<Base001UserEntity> findAll(Condition condition, FsPagination pagination, Sort sort) {
        // Initialize selection options with pagination and enable count query
        var options = pagination.toSelectOptions().count();
        // Perform the select operation using the custom DAO
        var entities = userDao.selectAll(condition, options, sort, toList());
        return new FsResultSet<>(entities, options.getCount());
    }

    /**
     * Retrieves the UIDs of all currently active users.
     *
     * @return A list of active user UIDs (String).
     */
    public List<String> findActiveUids() {
        return userDao.selectActiveUids();
    }

    /**
     * Finds a user entity by their unique email address.
     *
     * @param email The email address of the user.
     * @return An Optional containing the {@link Base001UserEntity} if found.
     */
    public Optional<Base001UserEntity> findUserByEmail(String email) {
        return userDao.selectUserByEmail(email);
    }

    /**
     * Finds a user entity by their unique identifier (UID).
     *
     * @param uid The UID of the user.
     * @return An Optional containing the {@link Base001UserEntity} if found.
     */
    public Optional<Base001UserEntity> findUserByUid(String uid) {
        return userDao.selectUserByUid(uid);
    }

    /**
     * Updates an existing user entity.
     *
     * @param entity The {@link Base001UserEntity} to update.
     */
    public void update(Base001UserEntity entity) {
        base001UserEntityDao.update(entity);
    }

    /**
     * Inserts a new user entity.
     *
     * @param entity The {@link Base001UserEntity} to create.
     */
    public void create(Base001UserEntity entity) {
        base001UserEntityDao.insert(entity);
    }

    /**
     * Saves a new user token entity (e.g., for device authentication/session).
     *
     * @param entity The {@link Base003UserTokenEntity} to save.
     */
    public void saveUserToken(Base003UserTokenEntity entity) {
        base003UserTokenEntityDao.insert(entity);
    }

    /**
     * Finds a user token entity by user ID and device ID.
     *
     * @param uid The user ID.
     * @param deviceId The unique ID of the device.
     * @return An Optional containing the {@link Base003UserTokenEntity} if found.
     */
    public Optional<Base003UserTokenEntity> findUserToken(String uid, String deviceId) {
        return userDao.selectUserToken(uid, deviceId);
    }

    /**
     * Updates an existing user token entity.
     *
     * @param entity The {@link Base003UserTokenEntity} to update.
     */
    public void updateUserToken(Base003UserTokenEntity entity) {
        base003UserTokenEntityDao.update(entity);
    }

    /**
     * Deletes a user token entity by its primary key (ID).
     *
     * @param entity The {@link Base003UserTokenEntity} to delete (only ID is required).
     */
    public void deleteUserToken(Base003UserTokenEntity entity) {
        base003UserTokenEntityDao.deleteById(entity.getId());
    }

    /**
     * Search Condition DTO for filtering users.
     */
    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @With
    public static class Condition
    {
        /**
         * Default search condition.
         */
        public static final Condition DEFAULT = new Condition();
        /**
         * Username (partial match search).
         */
        private String username;
        /**
         * Email address (partial match search).
         */
        private String email;
    }

    /**
     * Sort Parameter DTO for defining sorting column and direction.
     */
    @AllArgsConstructor
    @Value
    public static class Sort
    {

        /**
         * Default sorting criteria (ID ascending).
         */
        public static final Sort DEFAULT =
            new Sort(SortColumn.ID, FsSort.Direction.ASC);

        /**
         * The column to sort by.
         */
        SortColumn column;

        /**
         * The direction of the sort (ASC or DESC).
         */
        FsSort.Direction direction;

        /**
         * Converts this sort parameter into a SQL statement format.
         *
         * @return SQL statement fragment (e.g., "column_name ASC").
         */
        public String toSql() {
            return column.getColumnName() + " " + direction.name();
        }

        /**
         * Creates a new Sort parameter from a generic {@link FsSort}.
         *
         * @param sort The generic FsSort object.
         * @return The specific UserRepository.Sort parameter.
         */
        public static Sort fromFsSort(FsSort sort) {
            return new Sort(SortColumn.fromPropertyName(sort.property()),
                sort.direction());
        }
    }

    /**
     * Enumeration of database columns that can be used for sorting.
     */
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum SortColumn
    {

        ID("id", "id");

        /**
         * Property name in DTO/Resource.
         */
        private final String propertyName;

        /**
         * Database column name.
         */
        private final String columnName;

        /**
         * Converts a property name string into the corresponding SortColumn enum value.
         *
         * @param propertyName The property name string.
         * @return The matching SortColumn.
         * @throws IllegalArgumentException if the property name is not supported.
         */
        public static SortColumn fromPropertyName(String propertyName) {
            return Arrays.stream(values())
                .filter(v -> v.propertyName.equals(propertyName))
                .findFirst()
                .orElseThrow(
                    () -> new IllegalArgumentException("propertyName = '" + propertyName + "' is not supported."));
        }
    }
}
