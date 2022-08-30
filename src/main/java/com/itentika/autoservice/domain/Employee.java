package com.itentika.autoservice.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")	
	private Long id;

    @Column(name = "name")
	private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    private Position position;
}
