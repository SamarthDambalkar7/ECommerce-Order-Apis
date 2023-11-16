package com.ecommerce.controller;

import com.ecommerce.dto.Response;
import com.ecommerce.models.OrderItem;
import com.ecommerce.service.OrderItemsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@Slf4j
public class OrderItemsController {

    @Autowired
    private OrderItemsServiceImpl orderItemsService;

    @Operation(summary = "Get All Order-Item", description = "Get All Order-items Order-items exist, else will return 'not found'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Items List retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No valid Order-Item found")
    })
    @GetMapping
    public ResponseEntity<?> getALlOrderItems() {

        Response<List<OrderItem>> listResponse = orderItemsService.getAllOrderItems();
        HttpStatus httpStatus = HttpStatus.valueOf(listResponse.getStatus().getCode());

        return new ResponseEntity<>(listResponse, httpStatus);
    }


    @Operation(summary = "Get Order-Item", description = "Get single order if Order-Item-Id is given, else will return 'not found'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Valid Order-Item found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemByOrderId(@PathVariable(name = "id", required = false) Integer orderItemId) {


        Response<OrderItem> orderItemResponse = orderItemsService.getOrderitem(orderItemId);
        HttpStatus httpStatus = HttpStatus.valueOf(orderItemResponse.getStatus().getCode());


        return new ResponseEntity<>(orderItemResponse, httpStatus);

    }


    @PostMapping
    @Operation(summary = "Create a new order item", description = "This endpoint allows you to create a new order item.")
    @ApiResponse(responseCode = "201", description = "Order item created successfully")
    public ResponseEntity<?> addNewOrderItem(@RequestBody OrderItem orderItem) {

        Response<Void> response = orderItemsService.addNewOrderItem(orderItem);
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus().getCode());

        return new ResponseEntity<>(response, httpStatus);

    }


    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Order-Item", description = "Existing Order-Item can be updated by passing Order-Item-ID and updated properties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order-Item Updated Successfully"),
            @ApiResponse(responseCode = "404", description = "Order-Item not found")
    })
    public ResponseEntity<?> updateExistingOrderItem(@PathVariable(name = "id") Long orderItemId, @RequestBody OrderItem orderItem) {

        Response<Void> response = orderItemsService.updateItemById(orderItemId, orderItem);
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus().getCode());

        return new ResponseEntity<>(response, httpStatus);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an Order-Item", description = "Existing Order-Item can be deleted by passing Order-Item-ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order-Item Deleted Successfully"),
            @ApiResponse(responseCode = "404", description = "Order-Item not found")
    })
    public ResponseEntity<?> deleteOrderItem(@PathVariable(name = "id") Long orderItemId) {

        Response<Void> response = orderItemsService.deleteItemById(orderItemId);
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus().getCode());

        return new ResponseEntity<>(response, httpStatus);
    }

}
