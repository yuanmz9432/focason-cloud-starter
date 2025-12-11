package com.focason.core.exception;

import java.io.Serial;

/**
 * FsRefreshTokenExpiredException
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class FsRefreshTokenExpiredException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "Refresh token was expired.";

    static {
        ERROR_CODE = FsErrorCode.ILLEGAL_REFRESH_TOKEN;
    }

    public FsRefreshTokenExpiredException() {
        super(ERROR_CODE, MESSAGE_TEMPLATE, new Object());
    }
}
