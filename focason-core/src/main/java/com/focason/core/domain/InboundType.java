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
public enum InboundType
{
    // 1:通常入庫, 2:返品入庫, 3:調達入庫, 4:棚卸差異調整入庫
    /**
     * 1:通常入庫
     */
    REGULAR_RECEIVING(1),

    /**
     * 2:返品入庫
     */
    RETURN_RECEIVING(2),

    /**
     * 3:調達入庫
     */
    PROCUREMENT_RECEIVING(3),

    /**
     * 4:棚卸差異調整入庫
     */
    INVENTORY_ADJUSTMENT_RECEIVING(4);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static InboundType of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("InboundStatus = '" + value + "' is not supported."));
    }
}
