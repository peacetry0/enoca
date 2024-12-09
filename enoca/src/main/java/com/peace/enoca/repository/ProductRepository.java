package com.peace.enoca.repository;

import com.peace.enoca.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository  extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Product  p SET p.stock = p.stock -:quantity WHERE p.id =:id AND p.stock >= :quantity")
    Integer reduceStock(@Param("id") Long id, @Param("quantity") Integer quantity) ;

    @Transactional
    @Modifying
    @Query("UPDATE Product  p SET p.stock = p.stock +:quantity WHERE p.id =:id")
    Integer increaseStock(@Param("id") Long id, @Param("quantity") Integer quantity);

    @Query("SELECT p.stock FROM Product p WHERE p.id = :id")
    Optional<Integer> findStockById(@Param("id") Long id);

}
