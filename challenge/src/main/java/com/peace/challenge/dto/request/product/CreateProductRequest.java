package com.peace.challenge.dto.request.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateProductRequest(@NotBlank
                                   @NotNull
                                   @Size(min = 10,max = 100)
                                   String name,

                                   @NotBlank
                                   @NotNull
                                   @Size(min = 10,max = 500)
                                   String description,

                                   @NotNull
                                   @DecimalMin("0.00")
                                   @Positive
                                   BigDecimal price,

                                   @NotNull
                                   @Min(10)
                                   @Positive
                                   Integer stock) {
}
