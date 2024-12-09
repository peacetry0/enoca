package com.peace.enoca.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCustomerRequest(

        @NotBlank
        @NotNull
        String name,


        @NotBlank
        @Email
        String email

       ) {
}
