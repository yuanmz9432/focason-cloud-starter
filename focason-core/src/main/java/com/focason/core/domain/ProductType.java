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
public enum ProductType
{
    /**
     * 1:通常商品
     */
    NORMAL(1),

    /**
     * 2:セット商品
     */
    SET_PRODUCT(2),

    /**
     * 3:同梱物
     */
    BUNDLE(3);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static ProductType of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("StoreStatus = '" + value + "' is not supported."));
    }
}
