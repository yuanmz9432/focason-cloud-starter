/*
 * Copyright 2023 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;


import java.io.Serial;

public class FsResourceAlreadyExistsException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "Resource '%s' specified '%s' was already exists.";

    static {
        ERROR_CODE = FsErrorCode.CONFLICT;
    }

    public FsResourceAlreadyExistsException(Class<?> resourceClass, Object object) {
        super(ERROR_CODE, MESSAGE_TEMPLATE, resourceClass.getSimpleName(), object);
    }
}
