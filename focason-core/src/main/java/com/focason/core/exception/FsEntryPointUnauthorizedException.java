/*
 * Copyright 2023 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;


import java.io.Serial;

public class FsEntryPointUnauthorizedException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "Access token is missing.";

    static {
        ERROR_CODE = FsErrorCode.MISSING_AUTH_TOKEN;
    }

    public FsEntryPointUnauthorizedException() {
        super(ERROR_CODE, MESSAGE_TEMPLATE, new Object());
    }
}
