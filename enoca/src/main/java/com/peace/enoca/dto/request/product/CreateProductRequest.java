package com.peace.enoca.dto.request.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateProductRequest(

        @NotBlank
        @NotNull
        String name,

        @NotBlank
        @NotNull
        String description,

        @NotNull
        @DecimalMin("0.00")
        @Positive
        BigDecimal price,


        @NotNull
        @Min(10)
        @Positive
        Integer stock,

        @NotNull
        Integer quantity) {
}
