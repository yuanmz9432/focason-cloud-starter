// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.attribute;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.seasar.doma.Domain;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Getter
@Domain(
    valueType = Long.class,
    factoryMethod = "of")
public final class ID<E> implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;
    private final Long value;

    private ID(final Long value) {
        this.value = value;
    }

    @JsonValue
    public String toString() {
        return String.valueOf(this.value);
    }

    public static <R> ID<R> of(final Long value) {
        try {
            Objects.requireNonNull(value);
            if (value <= 0L) {
                throw new IllegalArgumentException("The identifier must be greater than 0.");
            }
        } catch (NullPointerException var2) {
            throw new IllegalArgumentException(var2);
        }

        return new ID<>(value);
    }

    @JsonCreator(
        mode = Mode.DELEGATING)
    public static <R> ID<R> of(final String value) {
        try {
            return of(Long.parseLong(value));
        } catch (NumberFormatException var2) {
            throw new IllegalArgumentException(var2);
        }
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ID<?> id = (ID) o;
            return this.value.equals(id.value);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @Component
    public static class IDConverter implements Converter<String, ID<?>>
    {
        public IDConverter() {}

        public ID<?> convert(@NotNull String value) {
            return ID.of(value);
        }
    }
}
