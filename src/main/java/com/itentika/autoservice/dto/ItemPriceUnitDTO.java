package com.itentika.autoservice.dto;

import com.itentika.autoservice.domain.ItemPriceUnit;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemPriceUnitDTO {
    private Long id;
    private String name;

    public ItemPriceUnitDTO(ItemPriceUnit itemPriceUnit) {
        this.id = itemPriceUnit.getId();
        this.name = itemPriceUnit.getName();
    }
}
