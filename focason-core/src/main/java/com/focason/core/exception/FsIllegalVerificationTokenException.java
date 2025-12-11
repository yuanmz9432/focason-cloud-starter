/*
 * Copyright 2023 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;


import java.io.Serial;

public class FsIllegalVerificationTokenException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "Verification token is invalid.";

    static {
        ERROR_CODE = FsErrorCode.BAD_REQUEST;
    }

    public FsIllegalVerificationTokenException() {
        super(ERROR_CODE, MESSAGE_TEMPLATE, new Object());
    }
}
