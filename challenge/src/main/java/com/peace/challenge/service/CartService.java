package com.peace.challenge.service;


import com.peace.challenge.convertor.CustomerMapper;
import com.peace.challenge.convertor.ProductMapper;
import com.peace.challenge.dto.response.customer.CustomerDTO;
import com.peace.challenge.dto.response.product.ProductDTO;
import com.peace.challenge.exception.CustomerNotFoundException;
import com.peace.challenge.model.Cart;
import com.peace.challenge.model.CartItem;
import com.peace.challenge.model.Customer;
import com.peace.challenge.model.Product;
import com.peace.challenge.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service

public class CartService {


    private final CartRepository cartRepository ;
    private final CustomerService customerService ;
    private final CustomerMapper customerMapper ;
    private final ProductService productService ;
    private final ProductMapper productMapper ;

    public CartService(CartRepository cartRepository, CustomerService customerService, CustomerMapper customerMapper, ProductService productService, ProductMapper productMapper) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    public Cart createCartForCustomer(Long customerId) {

        CustomerDTO customerDTO = customerService.getCustomerById(customerId);

        Customer customer = customerMapper.toEntity(customerDTO) ;

        Cart cart = new Cart() ;


    //    cart.setCustomer(customer);

        return cartRepository.save(cart) ;
    }

    public Cart addProductToCart(Long cartId, Long productId, int quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        ProductDTO product = productService.getProductById(productId);

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {

            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {

            CartItem newItem = new CartItem();
            newItem.setProduct(productMapper.toEntity(product));
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }
    public Cart removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().removeIf(cartItem -> cartItem.getProduct().getId().equals(productId));

        return cartRepository.save(cart);
    }
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().clear();
        cartRepository.save(cart);
    }
    public BigDecimal getTotalPrice(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cart.getItems()) {
            BigDecimal itemTotal = item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity()));
            total = total.add(itemTotal);
        }

        return total;
    }
}
