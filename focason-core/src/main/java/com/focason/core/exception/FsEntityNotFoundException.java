/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.exception;



import com.focason.core.attribute.ID;
import java.io.Serial;

public class FsEntityNotFoundException extends FsException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final FsErrorCode ERROR_CODE;
    private static final String MESSAGE_TEMPLATE = "Entity '%s' specified '%s' = '%s' does not exists.";

    static {
        ERROR_CODE = FsErrorCode.RESOURCE_NOT_FOUND;
    }

    public FsEntityNotFoundException(String target) {
        super(ERROR_CODE, "'%s' was not existed.", target);
    }

    public FsEntityNotFoundException(Class<?> entityClass, ID<?> id) {
        super(ERROR_CODE, MESSAGE_TEMPLATE, entityClass.getSimpleName(), "id", id);
    }

    public FsEntityNotFoundException(Class<?> entityClass, String email) {
        super(ERROR_CODE, MESSAGE_TEMPLATE, entityClass.getSimpleName(), "email", email);
    }

    public FsEntityNotFoundException(Class<?> entityClass, String targetName, String targetCode) {
        super(ERROR_CODE, MESSAGE_TEMPLATE, entityClass.getSimpleName(), targetName, targetCode);
    }
}
