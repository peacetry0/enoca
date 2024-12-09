package com.peace.enoca.model;

import jakarta.persistence.*;

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

    @Column(name = "quantity")
    private Integer quantity ;

   /* @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart=new Cart();*/

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    public Product() {
    }

    public Product(String name, BigDecimal price, Integer stock, String description, Integer quantity, Cart cart, List<Order> orders) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.quantity = quantity;
      //  this.cart = cart;
        this.orders = orders;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

  /*  public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }*/

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
