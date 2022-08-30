package com.itentika.autoservice.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "positions")
public class Position {
    @Id
    @Column(name = "id")	
	private Long id;
    
    @Column(name = "title")
	private String title;
}
