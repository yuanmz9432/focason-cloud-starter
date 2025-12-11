package com.focason.core.exception;


import java.io.Serial;

public class FsResetPasswordFailedException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "Reset password failed: %s.";

    static {
        ERROR_CODE = FsErrorCode.ILLEGAL_ACCESS_TOKEN;
    }

    public FsResetPasswordFailedException(String email) {
        super(ERROR_CODE, MESSAGE_TEMPLATE, email);
    }
}
