package com.ecommerce.service;

import com.ecommerce.dto.OrderDetails;
import com.ecommerce.dto.Response;
import com.ecommerce.models.Order;
import com.ecommerce.models.OrderItem;

import java.util.List;

public interface OrderService {

    //GET Requests
    Response<Order> getOrderByID(long orderId);

    Response<List<OrderDetails>> getAllOrders();

    //POST Requests
    Response<Void> addNewOrder(Order order);

    Response<List<OrderItem>> getDetailedOrdersList(long orderId);

    //PUT Requests
    boolean updateOrderByID(long orderId, Order order);

    //DELETE Requests
    boolean deleteOrderByID(long orderId);


}
