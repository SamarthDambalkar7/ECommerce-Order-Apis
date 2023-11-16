package com.ecommerce.service;

import com.ecommerce.dto.Response;
import com.ecommerce.models.OrderItem;

import java.util.List;

public interface OrderItemsService {

    //get requests


    Response<OrderItem> getOrderitem(long itemId);

    Response<List<OrderItem>> getAllOrderItems();

    //post request
    Response<Void> addNewOrderItem(OrderItem orderItem);

    //put request
    Response<Void> updateItemById(long itemId, OrderItem orderItem);

    //delete request
    Response<Void> deleteItemById(long itemId);

   
}
