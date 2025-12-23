/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;


import java.io.Serial;

public class FsInvalidPasswordException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "The password is invalid.";

    static {
        ERROR_CODE = FsErrorCode.INVALID_PASSWORD;
    }

    public FsInvalidPasswordException() {
        super(ERROR_CODE, MESSAGE_TEMPLATE);
    }
}
