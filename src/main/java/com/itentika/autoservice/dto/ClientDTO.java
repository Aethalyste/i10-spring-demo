package com.itentika.autoservice.dto;

import com.itentika.autoservice.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDTO {
	private Long id;
	private String name;
	private String phoneNumber;
	private Date birthDate;
	private String address;

	public ClientDTO(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.phoneNumber = client.getPhoneNumber();
		this.birthDate = client.getBirthDate();
		this.address = client.getAddress();
	}
}
