package com.peace.challenge.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    @Column(name = "name")
    private String name ;

    @Column(name = "price")
    private BigDecimal price ;

    @Column(name = "stock")
    private Integer stock ;

    @Column(name = "description")
    private String description ;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public Product() {
    }

    public Product(String name, BigDecimal price, Integer stock, String description, List<CartItem> cartItems) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.cartItems = cartItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
