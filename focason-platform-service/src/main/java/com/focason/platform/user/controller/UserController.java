// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.platform.user.controller;


import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.relativeTo;

import com.focason.platform.user.repository.UserRepository;
import com.focason.platform.user.service.UserService;
import com.focason.core.annotation.FsConditionParam;
import com.focason.core.annotation.FsPaginationParam;
import com.focason.core.annotation.FsSortParam;
import com.focason.core.attribute.FsPagination;
import com.focason.core.attribute.FsResultSet;
import com.focason.core.attribute.FsSort;
import com.focason.core.request.UserCreateRequest;
import com.focason.core.request.UserUpdateRequest;
import com.focason.core.resource.UserResource;
import com.focason.core.response.ActiveUserResponse;
import com.focason.core.response.UserFetchResponse;
import com.focason.core.response.UserSearchResponse;
import com.focason.core.utility.FsUtilityToolkit;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * UserController
 * <p>
 * REST Controller responsible for managing user-related operations, including
 * searching, fetching, creating, and updating user profiles.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RefreshScope // Allows dynamic refresh of configuration properties at runtime
@AllArgsConstructor(onConstructor = @__({
    @Autowired
}))
public class UserController
{

    final Logger logger = LoggerFactory.getLogger(UserController.class);

    /** Service layer for user business logic. */
    private final UserService service;
    private final String SEARCH_USER_URL = "/api/v1/users";
    private final String FETCH_USER_URL = "/api/v1/users/{uid}";
    private final String CREATE_USER_URL = "/api/v1/users";
    private final String UPDATE_USER_URL = "/api/v1/users/{uid}";
    private final String FETCH_ACTIVE_USER_URL = "/api/v1/users/active";

    /**
     * Searches for users based on specified conditions, pagination, and sorting.
     * Maps the result entities to {@link UserSearchResponse}.
     *
     * @param condition The custom search conditions encapsulated in {@link UserRepository.Condition}.
     * @param pagination The pagination parameters (page, size) from {@link FsPagination}.
     * @param fsSort The generic sorting parameters from {@link FsSort}.
     * @return {@link ResponseEntity} containing a paginated result set of {@link UserSearchResponse}.
     */
    @RequestMapping(method = RequestMethod.GET, value = SEARCH_USER_URL)
    public ResponseEntity<FsResultSet<UserSearchResponse>> search(@FsConditionParam UserRepository.Condition condition,
        @FsPaginationParam FsPagination pagination,
        @FsSortParam(allowedValues = {}) FsSort fsSort) {

        // Use default condition if none is provided
        if (condition == null) {
            condition = UserRepository.Condition.DEFAULT;
        }

        // Convert generic FsSort to repository-specific Sort
        var sort = UserRepository.Sort.fromFsSort(fsSort);

        // Delegate search to the service layer
        var users = service.search(condition, pagination, sort);

        // Convert entities to response DTOs and return the result set
        return ResponseEntity.ok(
            new FsResultSet<>(FsUtilityToolkit.convert(users.getData(), UserSearchResponse.class), users.getCount()));
    }

    /**
     * Fetches a single user's detailed information by their UUID.
     *
     * @param uuid The unique identifier of the user.
     * @return {@link ResponseEntity} containing the fetched user data mapped to {@link UserFetchResponse}.
     */
    @RequestMapping(method = RequestMethod.GET, value = FETCH_USER_URL)
    public ResponseEntity<UserFetchResponse> fetch(@PathVariable("uid") String uuid) {
        var resource = service.fetch(uuid);
        var response = FsUtilityToolkit.convert(resource, UserFetchResponse.class);
        // Additional mapping logic (commented out in original code) would go here
        return ResponseEntity.ok(response);
    }

    /**
     * Creates a new user and returns a 201 Created response with the URI of the newly created resource.
     * Uses {@link UriComponentsBuilder} to construct the resource URI.
     *
     * @param request The data for creating the user.
     * @param uriBuilder {@link UriComponentsBuilder} for constructing the Location header.
     * @return The 201 Created Response with the Location header pointing to the new user resource.
     */
    @RequestMapping(method = RequestMethod.POST, value = CREATE_USER_URL)
    public ResponseEntity<Void> create(@RequestBody UserCreateRequest request,
        UriComponentsBuilder uriBuilder) {

        // Convert request to resource and delegate creation to service
        final String uuid = service.create(FsUtilityToolkit.convert(request, UserResource.class));

        // Construct the URI for the new resource using method reference
        var uri = relativeTo(uriBuilder)
            .withMethodCall(on(getClass()).fetch(uuid))
            .build()
            .encode()
            .toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * Updates an existing user's information by their UUID.
     *
     * @param uuid The unique identifier of the user to update.
     * @param request The updated data for the user.
     * @return The 204 No Content response, indicating successful update.
     */
    @RequestMapping(method = RequestMethod.PUT, value = UPDATE_USER_URL)
    public ResponseEntity<Void> update(@PathVariable("uid") String uuid,
        @RequestBody UserUpdateRequest request) {
        service.update(uuid, FsUtilityToolkit.convert(request, UserResource.class));
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves the IDs (UIDs) of all currently active users.
     * This is typically used by services like the notification system for broadcasting.
     *
     * @return {@link ResponseEntity} containing the {@link ActiveUserResponse} which holds the list of active UIDs.
     */
    @RequestMapping(method = RequestMethod.GET, value = FETCH_ACTIVE_USER_URL)
    public ResponseEntity<ActiveUserResponse> getActiveUserIds() {
        return ResponseEntity.ok(service.getActiveUids());
    }
}
