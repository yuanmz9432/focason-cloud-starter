/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;


import java.io.Serial;

public class FsAccessTokenExpiredException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "Access token was expired.";

    static {
        ERROR_CODE = FsErrorCode.ILLEGAL_ACCESS_TOKEN;
    }

    public FsAccessTokenExpiredException() {
        super(ERROR_CODE, MESSAGE_TEMPLATE, new Object());
    }
}
