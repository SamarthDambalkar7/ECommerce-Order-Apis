package com.ecommerce.service;

import com.ecommerce.dto.OrderDetails;
import com.ecommerce.dto.Response;
import com.ecommerce.models.Order;
import com.ecommerce.models.OrderItem;

import java.util.List;

public interface OrderService {

    //GET Requests
    Response<OrderDetails> getOrderByID(long orderId);

    Response<List<OrderDetails>> getAllOrders();

    //POST Requests
    Response<Void> addNewOrder(Order order);

    Response<List<OrderItem>> getDetailedOrdersList(long orderId);

    //PUT Requests
    Response<Void> updateOrderByID(long orderId, Order order);

    //DELETE Requests
    Response<Void> deleteOrderByID(long orderId);

    //Utility Methods
    List<OrderItem> getOrderItemsFromDB(long orderId);


}
