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
public enum FileTaskStatus
{
    /**
     * 1:未処理
     */
    PENDING(1),
    /**
     * 2:完全読み込み済み
     */
    IMPORTED(2),
    /**
     * 3:完全処理済み
     */
    PROCESSED(3),
    /**
     * 4:異常あり
     */
    ERROR(4),
    /**
     * 5:キャンセル済み
     */
    CANCELED(5);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static FileTaskStatus of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("FIleType = '" + value + "' is not supported."));
    }
}
