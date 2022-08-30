package com.itentika.autoservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "positions")
public class Position {
    @Id
    @Column(name = "id")	
	private Long id;
    
    @Column(name = "title")
	private String title;

    public Position(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
