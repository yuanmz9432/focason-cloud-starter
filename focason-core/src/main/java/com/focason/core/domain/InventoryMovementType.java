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
public enum InventoryMovementType
{
    /**
     * 1:受領上棚
     */
    SHELVING(1),

    /**
     * 2:ピッキング移動
     */
    PICKING_MOVEMENT(2),

    /**
     * 3:補充移動
     */
    REPLENISHMENT_MOVEMENT(3),

    /**
     * 4:調達移動
     */
    TRANSFER_MOVEMENT(4),

    /**
     * 5:返品処理
     */
    RETURNS_PROCESSING(5),

    /**
     * 6:在庫整理
     */
    INVENTORY_ADJUSTMENT(6),

    /**
     * 7:品質検査移動
     */
    QUALITY_INSPECTION_MOVEMENT(7);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static InventoryMovementType of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("InventoryMovementType = '" + value + "' is not supported."));
    }
}
