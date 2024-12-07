package com.peace.challenge.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order extends BaseEntity {


    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice ;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate ;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer ;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>() ;

    public Order() {
    }

    public Order(BigDecimal totalPrice, Customer customer, LocalDateTime orderDate, List<OrderItem> orderItems) {
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
