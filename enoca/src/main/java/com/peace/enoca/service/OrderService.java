package com.peace.enoca.service;


import com.peace.enoca.convertor.OrderMapper;
import com.peace.enoca.dto.request.order.CreateOrderRequest;
import com.peace.enoca.dto.request.product.StockRequest;
import com.peace.enoca.exception.InsufficientStockException;
import com.peace.enoca.exception.OrderCodeNotFoundException;
import com.peace.enoca.model.Customer;
import com.peace.enoca.model.Order;
import com.peace.enoca.model.Product;
import com.peace.enoca.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository ;
    private final OrderMapper orderMapper ;
    private final CustomerService customerService ;
    private final ProductService productService ;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, CustomerService customerService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerService = customerService;
        this.productService = productService;
    }

    public Order getOrderByOrderCode(String orderCode) {

        return orderRepository.findByOrderCode(orderCode)

                .orElseThrow(() -> new OrderCodeNotFoundException("OrderCode not found: " + orderCode));
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {

        Customer customer = customerService.getCustomerById(customerId);

        return orderRepository.findByCustomer(customer);
    }

    public Order createOrder(final CreateOrderRequest createOrderRequest) {

        Customer customer = customerService.getCustomerById(createOrderRequest.customerId());


        List<Product> productList = new ArrayList<>();


        for (Long productId : createOrderRequest.productIds()) {

            Product product = productService.getProductById(productId);


            if (product.getStock() > 0) {
                productService.reduceStock(productId, new StockRequest(1));

                productList.add(product);

            } else {
                throw new InsufficientStockException("Insufficient stock for product ID: " + productId);
            }
        }


        Order order = orderMapper.toOrder(createOrderRequest, customer, productList);


        return orderRepository.save(order);
    }


    public Order getOrderById(final Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void deleteOrderById(final Long id) {

        Order order = getOrderById(id);


        order.getProducts().forEach(product ->

                productService.increaseStock(product.getId(), new StockRequest(1))
        );

        orderRepository.deleteById(id);
    }



}
