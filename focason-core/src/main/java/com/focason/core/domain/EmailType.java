/*
 * Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.domain;


import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.seasar.doma.Domain;

@Domain(valueType = Integer.class, factoryMethod = "of")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EmailType
{
    /**
     * 1:Register
     */
    REGISTER(1, "Your verification code for Focason Lab", "register.ftl"),

    /**
     * 2:Forgot Password
     */
    FORGOT_PASSWORD(2, "Reset your password", "forgot-password.ftl"),;

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;
    @Getter(onMethod = @__(@JsonValue))
    private final String subject;
    @Getter(onMethod = @__(@JsonValue))
    private final String template;

    public static EmailType of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("EmailType = '" + value + "' is not supported."));
    }
}
