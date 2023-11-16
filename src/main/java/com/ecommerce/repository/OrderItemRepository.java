package com.ecommerce.repository;

import com.ecommerce.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi from OrderItem oi WHERE oi.isDeleted = false")
    List<OrderItem> findNonDeletedOrderItems();
}
