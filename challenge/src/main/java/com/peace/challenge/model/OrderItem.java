package com.peace.challenge.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @Column(name = "quantity")
    private Integer quantity ;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product ;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order ;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, Product product, Order order) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
