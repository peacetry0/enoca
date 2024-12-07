package com.peace.challenge.dto.cart;

import com.peace.challenge.model.CartItem;

import java.math.BigDecimal;

public record CartItemDTO(Long productId,
                          String productName,
                          BigDecimal productPrice,
                          int quantity,
                          BigDecimal totalPrice) {

    public CartItemDTO(CartItem cartItem) {
        this(
                cartItem.getProduct().getId(),
                cartItem.getProduct().getName(),
                cartItem.getProduct().getPrice(),
                cartItem.getQuantity(),
                cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity()))
        );
    }
}
