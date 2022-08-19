package com.itentika.autoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ClientDTO {
	private Long id;
	private String name;
	private String phoneNumber;
	private Date birthDate;
	private String address;
}
