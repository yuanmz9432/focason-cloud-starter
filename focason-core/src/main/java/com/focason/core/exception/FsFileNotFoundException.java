package com.focason.core.exception;


import java.io.Serial;

public class FsFileNotFoundException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "File '%s' does not exists.";

    static {
        ERROR_CODE = FsErrorCode.RESOURCE_NOT_FOUND;
    }

    public FsFileNotFoundException(Object object) {
        super(ERROR_CODE, MESSAGE_TEMPLATE, object);
    }
}
