package com.itentika.autoservice.dto;

import com.itentika.autoservice.domain.ItemPrice;
import com.itentika.autoservice.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderItemDTO {
    private Long id;
    private Long quantity;
    private Double cost;
    private ItemPriceDTO priceItem;

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.cost = orderItem.getCost();
        this.priceItem = new ItemPriceDTO(orderItem.getItemPrice());
    }
}
