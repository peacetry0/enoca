package com.peace.challenge.dto.request.customer;

import jakarta.validation.constraints.*;

public record CreateCustomerRequest(
        @NotBlank
        @NotNull
        @Size(min = 10,max = 100)
        String name,


        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
         String password) {
}
