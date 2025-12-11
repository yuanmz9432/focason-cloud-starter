/*
 * Copyright 2023 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;


import java.io.Serial;

public class FsIllegalAccessTokenException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "Access token is invalid.";

    static {
        ERROR_CODE = FsErrorCode.ILLEGAL_ACCESS_TOKEN;
    }

    public FsIllegalAccessTokenException() {
        super(ERROR_CODE, MESSAGE_TEMPLATE, new Object());
    }

    public FsIllegalAccessTokenException(String errorMessage) {
        super(ERROR_CODE, errorMessage, new Object());
    }
}
