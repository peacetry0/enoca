package com.peace.challenge.dto.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartDTO(Long id,
                      Long customerId,
                      List<CartItemDTO> items,
                      BigDecimal totalPrice) {

    public CartDTO(com.peace.challenge.model.Cart cart) {
        this(
                cart.getId(),
                cart.getCustomer().getId(),
                cart.getItems().stream()
                        .map(CartItemDTO::new)
                        .toList(),
                cart.getItems().stream()
                        .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }
}
