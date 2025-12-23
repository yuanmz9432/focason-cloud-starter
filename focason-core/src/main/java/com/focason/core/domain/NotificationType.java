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
public enum NotificationType
{
    /**
     * 1:BROADCAST
     */
    BROADCAST(1),

    /**
     * 2:UNICAST
     */
    UNICAST(2);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static NotificationType of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("NotificationType = '" + value + "' is not supported."));
    }
}
