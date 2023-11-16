package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {

    private long orderId;
    private String customerName;
    private String address;
    private List<OrderItemDetails> orderItemsList;
}
