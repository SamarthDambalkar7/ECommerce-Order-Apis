package com.ecommerce.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "Order_Item")
@AllArgsConstructor
@Data
@Transactional
public class OrderItem {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_Id")
    @Schema(hidden = true)
    private long itemId;

    @Column(name = "item_Name")
    @NotNull
    private String itemName;

    @Column(name = "item_Price")
    @NotNull
    private BigDecimal itemPrice;

    @Column(name = "quantity")
    @NotNull
    @Min(1)
    @Max(5)
    private Integer quantity;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @Schema(hidden = true)
    private Order order;

    public OrderItem() {
        this.isDeleted = false;
    }


}
