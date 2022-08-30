package com.itentika.autoservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "order_history")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    private Order order;

    public OrderHistory(Order order, OrderStatus status, String comment, Date createdDate) {
        this.order = order;
        this.status = status;
        this.comment = comment;
        this.createdDate = createdDate;

        order.addOrderHistory(this);
    }
}
