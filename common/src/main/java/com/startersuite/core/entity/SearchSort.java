package com.startersuite.core.entity;

import com.startersuite.core.constant.ErrorCode;
import com.startersuite.core.exception.BusinessException;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class SearchSort {

    @Setter
    private List<SortOrder> orders;

    private SearchSort(List<SortOrder> orders) {
        this.orders = Collections.unmodifiableList(orders);
    }

    public static SearchSort by(List<SortOrder> orders) {
        if (orders == null || orders.isEmpty()) {
            throw BusinessException.build(ErrorCode.FAIL_BADREQUEST, "orders must not be null");
        }
        return new SearchSort(orders);
    }

    public List<SortOrder> sorts() {
        return this.orders;
    }

    public static enum SortDirection {

        ASC, DESC;

        public static SortDirection fromString(String value) {

            try {
                return SortDirection
                        .valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format(
                        "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).",
                        value), e);
            }
        }

        public boolean isAscending() {
            return this.equals(ASC);
        }

        public boolean isDescending() {
            return this.equals(DESC);
        }
    }

    public static class SortOrder implements Serializable {

        private final SortDirection direction;
        private final String property;

        private SortOrder(SortDirection direction, String property) {
            this.direction = direction;
            this.property = property;
        }

        public static SortOrder asc(String property) {
            return new SortOrder(
                    SortDirection.ASC, property);
        }

        public static SortOrder desc(String property) {
            return new SortOrder(
                    SortDirection.DESC, property);
        }

        public SortDirection getDirection() {
            return direction;
        }

        public String getProperty() {
            return property;
        }

    }
}
