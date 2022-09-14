package com.itentika.autoservice.domain;

import com.itentika.autoservice.dto.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.dao.DataAccessException;

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

    @OneToMany(mappedBy="order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy="order", cascade = CascadeType.PERSIST)
    private List<OrderHistory> orderHistoryList = new ArrayList<>();

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
        this.orderHistoryList.add(orderHistoryEntry);
    }

    public OrderHistory getLastHistoryEntry() {
//        orderHistory.sort(Comparator.comparing(OrderHistory::getCreatedDate).reversed());
        return orderHistoryList.size() > 0 ? orderHistoryList.get(orderHistoryList.size() - 1) : null;
    }

    public OrderStatus getStatus() {
        return getLastHistoryEntry() != null ? getLastHistoryEntry().getStatus() : OrderStatus.NEW;
    }

    public void accept(Employee master) {
        if (this.getStatus() != OrderStatus.NEW) {
            throw new DataAccessException("Order is in invalid state") {};
        }

        this.master = master;
        new OrderHistory(this, OrderStatus.ACCEPTED, "Order accepted", new Date());
    }

    public void updateStatus(OrderStatus status, String comment) {
        new OrderHistory(this, status, comment, new Date());
    }

    public void addItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public void update(OrderDTO orderDTO) {
        this.reason = orderDTO.getReason();
        this.beginDate = orderDTO.getBeginDate();
        this.endDate = orderDTO.getEndDate();
        this.comment = orderDTO.getComment();
    }
}
