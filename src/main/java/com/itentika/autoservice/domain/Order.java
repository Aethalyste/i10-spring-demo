package com.itentika.autoservice.domain;

import com.itentika.autoservice.dto.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
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

    @OneToMany(mappedBy="order", cascade = CascadeType.PERSIST)
    private List<OrderHistory> orderHistory = new ArrayList<>();

    @ManyToOne
    private Employee master;

    @ManyToOne
    private Employee administrator;

    public Order(OrderDTO orderDTO, Client client, Employee administrator) {
        this.reason = orderDTO.getReason();
        this.beginDate = orderDTO.getBeginDate();
        this.endDate = orderDTO.getEndDate();
        this.comment = orderDTO.getComment();
        this.client = client;
        this.administrator = administrator;

        new OrderHistory(this, OrderStatus.NEW, "This is a new order", new Date());
    }

    public void addOrderHistory(OrderHistory orderHistoryEntry) {
        this.orderHistory.add(orderHistoryEntry);
    }

    public OrderHistory getLastHistoryEntry() {
//        orderHistory.sort(Comparator.comparing(OrderHistory::getCreatedDate).reversed());
        return orderHistory.size() > 0 ? orderHistory.get(orderHistory.size() - 1) : null;
    }

    public OrderStatus getStatus() {
        return getLastHistoryEntry() != null ? getLastHistoryEntry().getStatus() : OrderStatus.NEW;
    }

    public void accept(Employee master) {
        if (this.getStatus() != OrderStatus.NEW) {
            throw new IllegalStateException("Order is in invalid state");
        }

        this.master = master;
        new OrderHistory(this, OrderStatus.ACCEPTED, "Order accepted", new Date());
    }
}
