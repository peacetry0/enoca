package com.peace.enoca.dto.request.customer;

import jakarta.validation.constraints.*;

public record CreateCustomerRequest(

        @NotBlank
        @NotNull
        String name,


        @NotBlank
        @Email
        String email,

        @NotBlank
        String password) {
}
