package com.itentika.autoservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "item_price_units")
public class ItemPriceUnit {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public ItemPriceUnit(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
