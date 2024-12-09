package com.peace.enoca.controller;



import com.peace.enoca.model.Cart;
import com.peace.enoca.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }



    @GetMapping("/{id}")
     public ResponseEntity<Cart> getCartById(@PathVariable final Long cartId) {

        this.cartService.getCart(cartId);

        return ResponseEntity.ok(cartService.getCart(cartId));
}

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Cart> createCartForCustomer(@PathVariable Long customerId) {
        Cart cart = cartService.createCartForCustomer(customerId);
        return ResponseEntity.ok(cart);
    }


    @PostMapping("/{cartId}/add-product/{productId}")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @RequestParam int quantity) {

        Cart cart = cartService.addProductToCart(cartId, productId, quantity);

        return ResponseEntity.ok(cart);
    }


    @DeleteMapping("/{cartId}/remove-product/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(

            @PathVariable Long cartId,
            @PathVariable Long productId) {

        Cart cart = cartService.removeProductFromCart(cartId, productId);

        return ResponseEntity.ok(cart);
    }


    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{cartId}/total-price")
    public ResponseEntity<BigDecimal> getTotalPrice(@PathVariable Cart cartId) {

        BigDecimal totalPrice = cartService.getTotalPrice(cartId);
        return ResponseEntity.ok(totalPrice);
    }
}
