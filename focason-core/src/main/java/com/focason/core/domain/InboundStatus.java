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
public enum InboundStatus
{
    /**
     * 1:未処理
     */
    UNPROCESSED(1),

    /**
     * 2:進行中
     */
    PROCESSING(2),

    /**
     * 3:入庫済み
     */
    DONE(3),

    /**
     * 4:キャンセル
     */
    CANCELLED(4),

    /**
     * 5:異常
     */
    EXCEPTION(5);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static InboundStatus of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("InboundStatus = '" + value + "' is not supported."));
    }
}
