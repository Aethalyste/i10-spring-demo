package com.itentika.autoservice.dto;

import com.itentika.autoservice.domain.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class OrderDTO {
	private Long id;
	private String reason;
	private Date beginDate;
	private Date endDate;
	private String comment;
	private ClientDTO client;
	private List<OrderItemDTO> orderItem = new ArrayList<>();
	private List<OrderHistoryDTO> orderHistory = new ArrayList<>();
	private EmployeeDTO master;
	private EmployeeDTO administrator;

	public OrderDTO(Order order) {
		this.id = order.getId();
		this.reason = order.getReason();
		this.beginDate = order.getBeginDate();
		this.endDate = order.getEndDate();
		this.comment = order.getComment();
		this.client = new ClientDTO(order.getClient());

		order.getOrderItems().forEach(orderItem -> this.orderItem.add(new OrderItemDTO(orderItem)));
		order.getOrderHistoryList().forEach(orderHistory -> this.orderHistory.add(new OrderHistoryDTO(orderHistory)));

		if (order.getMaster() != null) {
			this.master = new EmployeeDTO(order.getMaster());
		}
		if (order.getAdministrator() != null) {
			this.administrator = new EmployeeDTO(order.getAdministrator());
		}
	}
}
