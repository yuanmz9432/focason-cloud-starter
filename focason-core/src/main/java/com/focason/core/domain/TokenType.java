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
public enum TokenType
{
    /**
     * 1:アクセストークン
     */
    ACCESS_TOKEN(1),

    /**
     * 2:認証トークン
     */
    VERIFICATION_TOKEN(2),

    /**
     * 3:リフレッシュトークン
     */
    REFRESH_TOKEN(3);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static TokenType of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("TokenType = '" + value + "' is not supported."));
    }
}
