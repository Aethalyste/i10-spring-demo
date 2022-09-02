package com.itentika.autoservice.dto;

import com.itentika.autoservice.domain.ItemPrice;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemPriceDTO {
    private Long id;
    private String item;
    private Double price;
    private ItemPriceUnitDTO itemPriceUnit;

    public ItemPriceDTO(ItemPrice itemPrice) {
        this.id = itemPrice.getId();
        this.item = itemPrice.getItem();
        this.price = itemPrice.getPrice();
        this.itemPriceUnit = new ItemPriceUnitDTO(itemPrice.getItemPriceUnit());
    }
}
