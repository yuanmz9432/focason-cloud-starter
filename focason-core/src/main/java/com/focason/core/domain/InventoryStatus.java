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
public enum InventoryStatus
{
    /**
     * 1:未検査
     */
    NOT_INSPECTED(1),

    /**
     * 2:検査合格
     */
    INSPECTION_PASSED(2),

    /**
     * 3:検査不合格
     */
    INSPECTION_FAILED(3);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static InventoryStatus of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("InventoryStatus = '" + value + "' is not supported."));
    }
}
