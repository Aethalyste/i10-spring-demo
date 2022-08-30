package com.itentika.autoservice.domain;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
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

    public Employee(String name, String phoneNumber, Position position) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.position = position;
    }
}
