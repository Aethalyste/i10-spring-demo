package com.itentika.autoservice.domain;

import com.itentika.autoservice.dto.OrderItemDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne
    private Order order;

    @ManyToOne
    private ItemPrice itemPrice;

    public OrderItem(Order order, ItemPrice itemPrice, Long quantity) {
        this.quantity = quantity;
        this.cost = itemPrice.getPrice() * quantity;

        this.order = order;
        this.itemPrice = itemPrice;

        order.addItem(this);
    }
}
