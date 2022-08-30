package com.itentika.autoservice.dto;

import com.itentika.autoservice.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class EmployeeDTO {
	private Long id;
	private String name;
	private String phoneNumber;
	private PositionDTO position;

	public EmployeeDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.phoneNumber = employee.getPhoneNumber();
		this.position = new PositionDTO(employee.getPosition());
	}
}
