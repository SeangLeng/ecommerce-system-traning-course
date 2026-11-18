package mptc.seangleng.ecommerce.common_core;

import lombok.Builder;

import java.util.List;

@Builder
public record PageResponse<T>(
        List<T> items,
        int page,
        int size,
        int totalPages,
        boolean hasNext
) {
}
