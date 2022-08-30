package com.itentika.autoservice.domain;

import com.itentika.autoservice.dto.ClientDTO;

import java.util.Date;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

    @Column(name = "name")
	private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "address")
    private String address;

    public Client(ClientDTO clientDTO) {
        this.name = clientDTO.getName();
        this.phoneNumber = clientDTO.getPhoneNumber();
        this.birthDate = clientDTO.getBirthDate();
        this.address = clientDTO.getAddress();
    }
}