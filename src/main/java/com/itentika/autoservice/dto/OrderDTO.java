package com.itentika.autoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
public class OrderDTO {
	private Long id;
	private String reason;
	private Date beginDate;
	private Date endDate;
	private String comment;
	private ClientDTO client;
	private Collection<OrderItemDTO> orderItem;
	private Collection<OrderHistoryDTO> orderHistory;
	private Long master;
	private Long administrator;
}
