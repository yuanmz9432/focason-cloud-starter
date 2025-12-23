/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;


import java.io.Serial;

public class FsIllegalUserException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "The User('%s') was enable.";

    static {
        ERROR_CODE = FsErrorCode.FORBIDDEN;
    }

    public FsIllegalUserException(String email) {
        super(ERROR_CODE, MESSAGE_TEMPLATE, email);
    }
}
