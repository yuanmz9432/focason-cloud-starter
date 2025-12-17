// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.user.service;


import com.focason.core.attribute.FsPagination;
import com.focason.core.attribute.FsResultSet;
import com.focason.core.domain.Switch;
import com.focason.core.entity.Base001UserEntity;
import com.focason.core.exception.FsIllegalUserException;
import com.focason.core.exception.FsResourceAlreadyExistsException;
import com.focason.core.exception.FsResourceNotFoundException;
import com.focason.core.resource.UserResource;
import com.focason.core.response.ActiveUserResponse;
import com.focason.core.utility.FsUtilityToolkit;
import com.focason.platform.user.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 * <p>
 * Service layer providing business logic for user management, including searching,
 * fetching, creating, updating, and handling unique constraints.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService
{

    final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    /**
     * Searches for user profiles based on specified conditions, pagination, and sorting.
     *
     * @param condition The search criteria encapsulated in {@link UserRepository.Condition}.
     * @param pagination The pagination settings.
     * @param sort The sorting criteria.
     * @return A result set containing paginated {@link UserResource} and the total count.
     */
    @Transactional
    public FsResultSet<UserResource> search(UserRepository.Condition condition, FsPagination pagination,
        UserRepository.Sort sort) {
        // Find user entities by conditions.
        var resultSet = repository.findAll(condition, pagination, sort);
        // Convert entities to resources.
        var users = FsUtilityToolkit.convert(resultSet.getData(), UserResource.class);
        return new FsResultSet<>(users, resultSet.getCount());
    }

    /**
     * Fetches a single user profile by their unique identifier (UID/UUID).
     *
     * @param uuid The unique ID of the user.
     * @return The fetched user data as {@link UserResource}.
     * @throws FsResourceNotFoundException if no user is found with the given UUID.
     */
    @Transactional
    public UserResource fetch(String uuid) {
        // Attempt to find the user by UID. Throws FsResourceNotFoundException if not present.
        var user = repository.findUserByUid(uuid)
            .orElseThrow(() -> new FsResourceNotFoundException(UserResource.class, uuid));
        // Convert entity to resource and return.
        return FsUtilityToolkit.convert(user, UserResource.class);
    }

    /**
     * Creates a new user in the system.
     * Performs a duplicate check on the email before proceeding.
     *
     * @param resource The user data for creation.
     * @return The unique ID (sub) of the newly created user.
     */
    @Transactional
    public String create(UserResource resource) {
        // 1. Check for duplicate email address.
        duplicateCheck(resource.getEmail());

        // 2. Generate or set user UID.
        final String uid;
        if (resource.getUid() != null) {
            // Use provided UID if available.
            uid = resource.getUid();
        } else {
            // Generate a new UUID if not provided.
            uid = FsUtilityToolkit.generateUUID();
        }
        resource.setUid(uid);

        // 3. Encrypt the password before persisting.
        resource.setPassword(FsUtilityToolkit.encryptPassword(resource.getPassword()));

        // 4. Register User entity.
        repository.create(FsUtilityToolkit.convert(resource, Base001UserEntity.class));
        logger.info("New user created with UID: {}", uid);
        return uid;
    }

    /**
     * Updates an existing user's status and username.
     *
     * @param uid The unique ID of the user to update.
     * @param resource The resource containing the new data (status and username).
     * @throws FsResourceNotFoundException if no user is found with the given UID.
     */
    @Transactional
    public void update(String uid, UserResource resource) {
        // Fetch the existing user entity.
        var user = repository.findUserByUid(uid)
            .orElseThrow(() -> new FsResourceNotFoundException(UserResource.class, uid));

        // Update fields based on the resource.
        user.setStatus(resource.getStatus());
        user.setUsername(resource.getUsername());

        // Persist the changes.
        repository.update(user);
        logger.info("User {} updated successfully.", uid);
    }

    /**
     * Checks if a user with the given email already exists.
     * Throws exceptions based on the existing user's status.
     *
     * @param email The email address to check for duplication.
     * @throws FsResourceAlreadyExistsException if the user is already registered and verified (Status ON).
     * @throws FsIllegalUserException if the user is registered but unverified (Status OFF), suggesting re-validation.
     */
    protected void duplicateCheck(String email) {
        var user = repository.findUserByEmail(email);
        if (user.isPresent()) {
            if (Switch.ON.getValue().equals(user.get().getStatus())) {
                // Already registered and verified successfully.
                throw new FsResourceAlreadyExistsException(UserResource.class, email);
            } else {
                // Registered but not verified (e.g., email not confirmed), allowing re-validation process.
                throw new FsIllegalUserException(email);
            }
        }
    }

    /**
     * Retrieves the UIDs of all currently active users in the system.
     *
     * @return An {@link ActiveUserResponse} containing a list of active UIDs.
     */
    @Transactional
    public ActiveUserResponse getActiveUids() {
        return new ActiveUserResponse(repository.findActiveUids());
    }
}
