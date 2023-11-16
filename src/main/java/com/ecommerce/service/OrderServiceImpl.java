package com.ecommerce.service;

import com.ecommerce.dto.OrderDetails;
import com.ecommerce.dto.OrderItemDetails;
import com.ecommerce.dto.Response;
import com.ecommerce.models.Order;
import com.ecommerce.models.OrderItem;
import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Implementation / Service Class.
 * Contains methods to perform CRUD on "Order".
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public Response<Order> getOrderByID(long orderId) {

        Response<Order> orderResponse = new Response<>();
        Response.Status status = new Response.Status();

        if (orderRepository.existsById(orderId)) {
            log.info("Order with OrderID: " + orderId + " Found!");


            status.setCode(200);
            status.setMessage("Order with OrderID: " + orderId + " Found!");

            orderResponse.setStatus(status);
            orderResponse.setData(orderRepository.findById(orderId).get());
        } else {
            log.info("Order with OrderID: " + orderId + " Not Found!");
            status.setCode(404);
            status.setMessage("Order with OrderID: " + orderId + " Not Found!");

            orderResponse.setStatus(status);
        }

        return orderResponse;

    }

    @Override
    public Response<List<OrderDetails>> getAllOrders() {
        Response<List<OrderDetails>> listResponse = new Response<>();
        Response.Status status = new Response.Status();
        OrderDetails orderDetails = new OrderDetails();
        OrderItemDetails orderItemDetails = new OrderItemDetails();
        List<OrderDetails> orderDetailsList = new LinkedList<>();
        List<OrderItemDetails> orderItemDetailsList = new ArrayList<>();

        List<Order> orderList = orderRepository.findNonDeletedOrderItems();

        if (orderList.isEmpty()) {
            log.info("No Orders Found");

            status.setCode(404);
            status.setMessage("No Orders Found");
        } else {
            log.info(orderList.size() + " Orders found");

            orderList.forEach(n -> {
                orderDetails.setOrderId(n.getOrderId());
                orderDetails.setAddress(n.getAddress());
                orderDetails.setCustomerName(n.getCustomerName());
                n.getOrderItems().forEach(i -> {
                    orderItemDetails.setItemId(i.getItemId());
                    orderItemDetails.setItemName(i.getItemName());
                    orderItemDetails.setItemPrice(i.getItemPrice());
                    orderItemDetailsList.add(orderItemDetails);
                });

                orderDetails.setOrderItemsList(orderItemDetailsList);
                orderDetailsList.add(orderDetails);
            });


            status.setCode(200);
            status.setMessage(orderList.size() + " Orders found");

        }

        listResponse.setData(orderDetailsList);
        listResponse.setStatus(status);

        return listResponse;

    }

    @Override
    public Response<Void> addNewOrder(Order order) {

        Response<Void> response = new Response<>();
        Response.Status status = new Response.Status();


        order.getOrderItems().forEach(n -> order.getOrderitemIds().add(n.getItemId()));

        log.error("{}", order);
        long savedOrderId = orderRepository.save(order).getOrderId();
        log.info("Order with Order-Id: " + savedOrderId + " added Successfully!");

        status.setCode(201);
        status.setMessage("Order with Order-Id: " + savedOrderId + " added Successfully!");
        response.setStatus(status);

        return response;
    }

    @Override
    public Response<List<OrderItem>> getDetailedOrdersList(long orderId) {

        Response<List<OrderItem>> listResponse = new Response<>();
        Response.Status status = new Response.Status();
        List<OrderItem> orderItemList = new ArrayList<>();

        Set<Long> orderItemIds = orderRepository.findOrderItemIdsByOrderId(orderId);

        orderItemIds.forEach(n -> orderItemList.add(orderItemRepository.findById(n).get()));
        log.info("Order: " + orderId + " contains " + orderItemList.size() + " Order-Items");


        status.setCode(200);
        status.setMessage("Order: " + orderId + " contains " + orderItemList.size() + " Order-Items");

        listResponse.setStatus(status);
        listResponse.setData(orderItemList);
        return listResponse;

    }

    @Override
    public boolean updateOrderByID(long orderId, Order order) {
        if (orderRepository.existsById(orderId)) {
            Order fetchedOrder = orderRepository.findById(orderId).get();

            fetchedOrder.setAddress(order.getAddress());
            fetchedOrder.setCustomerName(order.getCustomerName());
            fetchedOrder.setOrderItems(order.getOrderItems());

            orderRepository.save(fetchedOrder);

            return true;

        }
        return false;
    }

    @Override
    public boolean deleteOrderByID(long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            return false;
        } else {
            order.setDeleted(true);
            orderRepository.save(order);
            return true;
        }

    }
}
