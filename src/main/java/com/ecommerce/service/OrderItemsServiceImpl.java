package com.ecommerce.service;

import com.ecommerce.dto.Response;
import com.ecommerce.models.OrderItem;
import com.ecommerce.repository.OrderItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation / Service Class.
 * Contains methods to perform CRUD on "Order-Items".
 */
@Service
@Slf4j
public class OrderItemsServiceImpl implements OrderItemsService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Response<OrderItem> getOrderitem(long itemId) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(itemId);

        Response<OrderItem> responseOrderItem = new Response<>();
        Response.Status status = new Response.Status();
        if (orderItem.isPresent()) {
            status.setCode(200);
            status.setMessage("Item with itemId: " + itemId + " found");
            responseOrderItem.setStatus(status);
            responseOrderItem.setData(orderItem.get());
        } else {
            log.info("item with itemId: " + itemId + " not found in DB!");
            status.setCode(404);
            status.setMessage("item with itemId: " + itemId + " not found!");
            responseOrderItem.setStatus(status);
        }
        return responseOrderItem;
    }

    @Override
    public Response<List<OrderItem>> getAllOrderItems() {

        Response<List<OrderItem>> listResponse = new Response<>();
        List<OrderItem> orderItemList = orderItemRepository.findNonDeletedOrderItems();
        Response.Status status = new Response.Status();

        if (orderItemList.isEmpty()) {
            log.info("No Order-Items found");
            status.setCode(404);
            status.setMessage("No Order-Items found");
            listResponse.setStatus(status);
        } else {
            status.setCode(200);
            status.setMessage(orderItemList.size() + " Order-Items found!");
            listResponse.setStatus(status);
            listResponse.setData(orderItemList);
        }

        return listResponse;
    }


    @Override
    public Response<Void> addNewOrderItem(OrderItem orderItem) {

        long savedOrderItemId = orderItemRepository.save(orderItem).getItemId();
        Response<Void> response = new Response<>();
        Response.Status status = new Response.Status();

        log.info("Order Item Added Successfully: " + orderItem);

        status.setCode(201);
        status.setMessage("Order Item Added Successfully: " + savedOrderItemId);
        response.setStatus(status);

        return response;

    }


    @Override
    public Response<Void> updateItemById(long itemId, OrderItem orderItem) {

        Response<Void> response = new Response<>();
        Response.Status status = new Response.Status();

        if (orderItemRepository.existsById(itemId)) {
            OrderItem fetchedOrderItem = orderItemRepository.findById(itemId).get();

            fetchedOrderItem.setItemName(orderItem.getItemName());
            fetchedOrderItem.setItemPrice(orderItem.getItemPrice());
            fetchedOrderItem.setQuantity(orderItem.getQuantity());

            orderItemRepository.save(fetchedOrderItem);
            log.info("Order-Item with id: " + itemId + " Updated Successfully");


            status.setCode(200);
            status.setMessage("Order-Item with id: " + itemId + " Updated Successfully");

        } else {

            status.setCode(400);
            status.setMessage("Order-Item with id: " + itemId + " Not Found");
            log.info("Order-Item with id: " + itemId + " Not Found");

        }
        response.setStatus(status);
        return response;


    }

    @Override
    public Response<Void> deleteItemById(long itemId) {

        Response<Void> response = new Response<>();
        Response.Status status = new Response.Status();

        if (orderItemRepository.existsById(itemId)) {
            OrderItem orderItem = orderItemRepository.findById(itemId).get();
            orderItem.setDeleted(true);
            orderItemRepository.save(orderItem);

            log.info("Order-item with ID: " + itemId + " Deleted Successfully!");

            status.setCode(200);
            status.setMessage("Order-item with ID: " + itemId + " Deleted Successfully!");
        } else {
            log.info("Order-item with ID: " + itemId + " Not Found!");
            status.setCode(404);
            status.setMessage("Order-item with ID: " + itemId + " Not Found!");
        }

        response.setStatus(status);
        return response;


    }


}
