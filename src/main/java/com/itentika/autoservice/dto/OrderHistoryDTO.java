package com.itentika.autoservice.dto;

import com.itentika.autoservice.domain.OrderHistory;
import com.itentika.autoservice.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class OrderHistoryDTO {
    private Long id;
    private OrderStatus status;
    private String comment;
    private Date createdDate;

    public OrderHistoryDTO(OrderHistory orderHistory) {
        this.id = orderHistory.getId();
        this.status = orderHistory.getStatus();
        this.comment = orderHistory.getComment();
        this.createdDate = orderHistory.getCreatedDate();
    }
}
