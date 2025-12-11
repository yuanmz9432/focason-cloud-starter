/*
 * Copyright 2023 Focason Co.,Ltd. AllRights Reserved.
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
public enum UserType
{

    /**
     * 1:ルートユーザー
     */
    ROOT(1),

    /**
     * 2:通常ユーザー
     */
    NORMAL(2);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static UserType of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("UserType = '" + value + "' is not supported."));
    }
}
