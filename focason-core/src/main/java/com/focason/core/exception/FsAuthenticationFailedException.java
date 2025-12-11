package com.focason.core.exception;

import java.io.Serial;

/**
 * FsAuthenticationFailedException
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class FsAuthenticationFailedException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "Authentication Failed.";

    static {
        ERROR_CODE = FsErrorCode.AUTHENTICATION_FAILED;
    }

    public FsAuthenticationFailedException() {
        super(ERROR_CODE, MESSAGE_TEMPLATE, new Object());
    }
}
