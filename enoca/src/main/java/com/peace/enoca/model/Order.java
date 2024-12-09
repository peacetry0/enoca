package com.peace.enoca.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {


    @Column(name = "total_price")
    private BigDecimal totalPrice ;

    @Column(name = "order_code")
    private String orderCode ;

    @CreationTimestamp
    @Column(name = "order_date")
    private LocalDateTime orderDate ;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer ;

    @ManyToMany
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    public Order() {
    }

    public Order(BigDecimal totalPrice, String orderCode, LocalDateTime orderDate, Customer customer, List<Product> products) {
        this.totalPrice = totalPrice;
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.customer = customer;
        this.products = products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
