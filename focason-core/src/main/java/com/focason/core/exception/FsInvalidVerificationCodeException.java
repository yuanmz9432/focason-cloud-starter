/*
 * Copyright 2023 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;


import java.io.Serial;

public class FsInvalidVerificationCodeException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "The verification code is invalid.";

    static {
        ERROR_CODE = FsErrorCode.ILLEGAL_VERIFICATION_CODE;
    }

    public FsInvalidVerificationCodeException() {
        super(ERROR_CODE, MESSAGE_TEMPLATE);
    }
}
