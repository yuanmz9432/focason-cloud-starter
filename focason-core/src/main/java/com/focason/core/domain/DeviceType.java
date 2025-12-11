// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.domain;


import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.seasar.doma.Domain;

@Domain(valueType = Integer.class, factoryMethod = "of")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DeviceType
{
    /**
     * 1:WEB
     */
    WEB(1),

    /**
     * 9:UNKNOWN
     */
    UNKNOWN(9);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static DeviceType of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("DeviceType = '" + value + "' is not supported."));
    }
}
