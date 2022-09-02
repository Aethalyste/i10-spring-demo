package com.itentika.autoservice.domain;

import com.itentika.autoservice.dto.ItemPriceDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "item_prices")
public class ItemPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    private ItemPriceUnit itemPriceUnit;

    public ItemPrice(String item, Double price, ItemPriceUnit itemPriceUnit) {
        this.item = item;
        this.price = price;
        this.itemPriceUnit = itemPriceUnit;
    }
}
