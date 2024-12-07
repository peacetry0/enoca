package com.peace.challenge.convertor;

import com.peace.challenge.dto.cart.CartDTO;
import com.peace.challenge.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        return new CartDTO(cart);
    }
}
