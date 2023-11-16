package com.ecommerce.controller;

import com.ecommerce.dto.OrderDetails;
import com.ecommerce.dto.Response;
import com.ecommerce.models.Order;
import com.ecommerce.models.OrderItem;
import com.ecommerce.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;


    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        Response<List<OrderDetails>> orderResponse = orderService.getAllOrders();
        HttpStatus httpStatus = HttpStatus.valueOf(orderResponse.getStatus().getCode());

        return new ResponseEntity<>(orderResponse, httpStatus);


    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderByOrderId(@PathVariable(name = "id", required = false) Long orderId) {

        Response<Order> orderResponse = orderService.getOrderByID(orderId);
        HttpStatus httpStatus = HttpStatus.valueOf(orderResponse.getStatus().getCode());

        return new ResponseEntity<>(orderResponse, httpStatus);
    }

    @GetMapping("/order-items/{id}")
    public ResponseEntity<?> getDetailedOrders(@PathVariable("id") Long id) {
        Response<List<OrderItem>> listResponse = orderService.getDetailedOrdersList(id);
        HttpStatus httpStatus = HttpStatus.valueOf(listResponse.getStatus().getCode());

        return new ResponseEntity<>(listResponse, httpStatus);

    }

    @PostMapping
    public ResponseEntity<?> addNewOrder(@RequestBody Order order) {
        Response<Void> response = orderService.addNewOrder(order);
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus().getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExistingOrder(@PathVariable(name = "id") Integer orderId, @RequestBody Order order) {
        return orderService.updateOrderByID(orderId, order) ? ResponseEntity.ok().body("Order: " + orderId + " Updated!")
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "id") Integer orderId) {
        return orderService.deleteOrderByID(orderId) ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("Order : " + orderId + " Deleted!")
                : ResponseEntity.notFound().build();
    }

}
