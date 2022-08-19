package com.itentika.autoservice.domain;

import com.itentika.autoservice.dto.OrderDTO;
import lombok.Getter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    private Client client;
//    private Collection<OrderItem> orderItems;
//    private Collection<OrderHistory> orderHistory;
//    private User master;
//    private User administrator;

    public Order(OrderDTO orderDTO, Client client) {
        this.reason = orderDTO.getReason();
        this.beginDate = orderDTO.getBeginDate();
        this.endDate = orderDTO.getEndDate();
        this.comment = orderDTO.getComment();
        this.client = client;
    }
}
