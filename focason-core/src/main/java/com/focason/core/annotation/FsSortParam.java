// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.annotation;



import java.lang.annotation.*;

@Target({
    ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FsSortParam
{
    String defaultValue() default "id:ASC";

    String[] allowedValues() default {
        "id:ASC"
    };
}
