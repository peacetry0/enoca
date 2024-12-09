package com.peace.enoca.convertor;

import com.peace.enoca.dto.request.order.CreateOrderRequest;
import com.peace.enoca.model.Customer;
import com.peace.enoca.model.Order;
import com.peace.enoca.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public Order toOrder(final CreateOrderRequest createOrderRequest, Customer customer, List<Product> productList){


        Order order = new Order();

        order.setCustomer(customer);

        order.setProducts(productList);
        return order;

    }
}
