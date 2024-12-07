package com.peace.challenge.controllers;

import com.peace.challenge.dto.cart.CartDTO;
import com.peace.challenge.model.Cart;
import com.peace.challenge.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1.0/carts")

public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create/{customerId}")
    public ResponseEntity<CartDTO> createCart(@PathVariable Long customerId) {

        Cart cart = cartService.createCartForCustomer(customerId);

        CartDTO cartDTO = new CartDTO(cart);

        return ResponseEntity.ok(cartDTO);
    }


    @PostMapping("/{cartId}/add-product/{productId}/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, @PathVariable int quantity) {

        Cart cart = cartService.addProductToCart(cartId, productId, quantity);

        CartDTO cartDTO = new CartDTO(cart);

        return ResponseEntity.ok(cartDTO);
    }


    @DeleteMapping("/{cartId}/remove-product/{productId}")
    public ResponseEntity<CartDTO> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {

        Cart cart = cartService.removeProductFromCart(cartId, productId);

        CartDTO cartDTO = new CartDTO(cart);

        return ResponseEntity.ok(cartDTO);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long cartId) {

        Cart cart = cartService.getCart(cartId);

        CartDTO cartDTO = new CartDTO(cart);

        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {

        cartService.clearCart(cartId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cartId}/total")
    public ResponseEntity<BigDecimal> getTotalPrice(@PathVariable Long cartId) {

        BigDecimal totalPrice = cartService.getTotalPrice(cartId);

        return ResponseEntity.ok(totalPrice);
    }
}
