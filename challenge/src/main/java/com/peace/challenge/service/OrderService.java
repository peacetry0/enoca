package com.peace.challenge.service;


import com.peace.challenge.dto.response.customer.CustomerDTO;
import com.peace.challenge.model.Customer;
import com.peace.challenge.model.Order;
import com.peace.challenge.model.OrderItem;
import com.peace.challenge.repository.OrderItemRepository;
import com.peace.challenge.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class OrderService {

    private final OrderRepository orderRepository ;
    private final CustomerService customerService ;
    private final OrderItemRepository orderItemRepository ;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.orderItemRepository = orderItemRepository;
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }


    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }



}
