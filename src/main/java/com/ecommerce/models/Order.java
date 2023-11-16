package com.ecommerce.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Orders")
@AllArgsConstructor
@Data
@Transactional
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    @Schema(hidden = true)
    private long orderId;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "customer_Name")
    @NotNull
    private String customerName;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @Column(name = "order_item_id")
    @ElementCollection
    @CollectionTable(name = "order_orderitemids", joinColumns = @JoinColumn(name = "order_id"))
    private Set<Long> orderitemIds = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Column(name = "orderItems")
    private List<OrderItem> orderItems;


    public Order() {
        this.isDeleted = false;
    }

}
