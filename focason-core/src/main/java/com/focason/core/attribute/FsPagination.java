// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.attribute;

import java.io.Serializable;
import org.jetbrains.annotations.NotNull;
import org.seasar.doma.jdbc.SelectOptions;

public record FsPagination(int limit, int page) implements Serializable {
    public static final FsPagination DEFAULT = of(20, 1);

    public static FsPagination of(int limit, int page) {
        // FsSort parameter is ignored here as per the original code's logic,
        // but the method signature is kept to match the original requirement.
        return of(limit, page, null);
    }

    public static FsPagination of(int limit, int page, FsSort sort) {
        if (limit < 1 && limit != -1) {
            throw new IllegalArgumentException("Page size (limit) must not be less than one!");
        } else if (page < 1) {
            throw new IllegalArgumentException("Page index (page) must not be less than one!");
        } else {
            // Note: The original implementation uses a builder pattern here,
            // which is maintained for consistency.
            return builder().limit(limit).page(page).build();
        }
    }

    public static FsPaginationBuilder builder() {
        return new FsPaginationBuilder();
    }

    /**
     * Converts the pagination state into Doma's SelectOptions format.
     */
    public SelectOptions toSelectOptions() {
        // Uses the mocked SelectOptions interface
        return SelectOptions.get().limit(this.limit).offset(this.limit * (this.page - 1));
    }

    public static SelectOptions toSelectOptionsWithNoPagination() {
        // Uses the mocked SelectOptions interface
        return SelectOptions.get();
    }

    public FsPagination next() {
        return this.withPage(this.page + 1);
    }

    public FsPagination first() {
        return this.withPage(1);
    }

    public FsPagination previousOrFirst() {
        return this.hasPrevious() ? this.withPage(this.page - 1) : this.first();
    }

    public boolean hasPrevious() {
        return this.page > 1;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof FsPagination other)) {
            return false;
        } else {
            if (this.limit() != other.limit()) {
                return false;
            } else {
                return this.page() == other.page();
            }
        }
    }

    public int hashCode() {
        int result = 59 + this.limit();
        result = result * 59 + this.page();
        return result;
    }

    @NotNull
    public String toString() {
        int var10000 = this.limit();
        return "FsPagination(limit=" + var10000 + ", page=" + this.page() + ")";
    }

    public FsPagination withLimit(final int limit) {
        return this.limit == limit ? this : new FsPagination(limit, this.page);
    }

    public FsPagination withPage(final int page) {
        return this.page == page ? this : new FsPagination(this.limit, page);
    }

    public static class FsPaginationBuilder {
        private int limit;
        private int page;

        FsPaginationBuilder() {
        }

        public FsPaginationBuilder limit(final int limit) {
            this.limit = limit;
            return this;
        }

        public FsPaginationBuilder page(final int page) {
            this.page = page;
            return this;
        }

        public FsPagination build() {
            return new FsPagination(this.limit, this.page);
        }

        public String toString() {
            return "FsPagination.FsPaginationBuilder(limit=" + this.limit + ", page=" + this.page + ")";
        }
    }}
