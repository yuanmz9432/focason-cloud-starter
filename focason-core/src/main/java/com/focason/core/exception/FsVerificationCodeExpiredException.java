package com.focason.core.exception;

import java.io.Serial;

/**
 * FsVerificationCodeExpiredException
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class FsVerificationCodeExpiredException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "Verification code was expired.";

    static {
        ERROR_CODE = FsErrorCode.VERIFICATION_CODE_EXPIRED;
    }

    public FsVerificationCodeExpiredException() {
        super(ERROR_CODE, MESSAGE_TEMPLATE, new Object());
    }
}
