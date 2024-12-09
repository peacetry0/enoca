package com.peace.enoca.repository;

import com.peace.enoca.model.Customer;
import com.peace.enoca.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByOrderCode(String orderCode);
    List<Order> findByCustomer(Customer customer);
}
