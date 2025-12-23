/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

public enum FsErrorCode
{
    // 400 Bad Request
    BAD_REQUEST("E400000"),

    VALIDATION_ERROR("E400001"),

    DATA_INTEGRITY_VIOLATION("E400002"),

    DUPLICATE_ENTRY("E400003"),

    ILLEGAL_STATE("E400004"),

    MISSING_AUTH_TOKEN("E400005"),

    ILLEGAL_ACCESS_TOKEN("E400006"),

    AUTHENTICATION_FAILED("E400007"),

    ILLEGAL_REFRESH_TOKEN("E400008"),

    ILLEGAL_VERIFICATION_CODE("E400009"),

    VERIFICATION_CODE_EXPIRED("E400010"),

    // 401 Unauthorized
    UNAUTHORIZED("E401000"),

    USER_AUTHENTICATION_FAILURE("E401001"),

    AUTH_TOKEN_INVALID("E401002"),

    AUTH_TOKEN_EXPIRED("E401003"),

    AUTH_TOKEN_REVOKED("E401004"),

    AUTH_HEADER_NOT_FOUND("E401005"),

    INVALID_PASSWORD("E401006"),

    // 403 Forbidden
    FORBIDDEN("E403000"),

    MLS_ACCESS_DENIED("E403001"),

    RLS_ACCESS_DENIED("E403002"),

    ACCOUNT_DISABLED("E403211"),

    API_KEY_ACCESS_DENIED("E403300"),

    REQUEST_REJECTED("E400999"),

    // 404 Not Found
    NOT_FOUND("E404000"),

    RESOURCE_NOT_FOUND("E404001"),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED("E405000"),

    // 409 Conflict
    CONFLICT("E409000"),

    MUTEX_LOCK_FAILED("E409001"),

    // 415 Unsupported Media Type
    UNSUPPORTED_MEDIA_TYPE("E415000"),

    INTERNAL_SERVER_ERROR("E500000"),

    PROCESS_TIMEOUT("E500001");

    private final String value;

    FsErrorCode(final String value) {
        this.value = value;
    }

    @JsonCreator
    public static FsErrorCode of(String value) {
        return Arrays.stream(values()).filter((v) -> v.value.equals(value)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("FsErrorCode = '" + value + "' is not supported."));
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
