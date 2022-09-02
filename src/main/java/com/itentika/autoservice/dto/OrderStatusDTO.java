package com.itentika.autoservice.dto;

import com.itentika.autoservice.domain.Order;
import com.itentika.autoservice.domain.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderStatusDTO {
	private Long orderId;
	private OrderStatus status;
	private String comment;

	public OrderStatusDTO(Order order) {
		this.orderId = order.getId();
		this.status = order.getLastHistoryEntry().getStatus();
		this.comment = order.getLastHistoryEntry().getComment();
	}
}
