package com.peace.enoca.dto.request.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StockRequest(

        @NotNull
        @Min(10)
        @Positive
        Integer quantity) {
}
