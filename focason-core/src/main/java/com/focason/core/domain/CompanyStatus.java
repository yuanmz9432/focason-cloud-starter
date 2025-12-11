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
public enum CompanyStatus
{
    /**
     * 1:稼働中
     */
    IN_OPERATION(1),

    /**
     * 2:非稼働中
     */
    OUT_OF_OPERATION(2),

    /**
     * 3:体験中
     */
    FREE_TRIAL(3),

    /**
     * 4:閉鎖中
     */
    LOCKED(4);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    public static CompanyStatus of(Integer value) {
        return Arrays.stream(values())
            .filter(v -> v.getValue().equals(value))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("CompanyStatus = '" + value + "' is not supported."));
    }
}
