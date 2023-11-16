package com.ecommerce.repository;

import com.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o from Order o WHERE o.isDeleted = false")
    List<Order> findNonDeletedOrderItems();

    @Query(value = "SELECT order_item_id FROM Order_OrderItemIds WHERE order_id = :orderId", nativeQuery = true)
    Set<Long> findOrderItemIdsByOrderId(@Param("orderId") Long OrderId);
}
