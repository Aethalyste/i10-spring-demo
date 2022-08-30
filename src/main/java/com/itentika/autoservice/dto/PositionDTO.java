package com.itentika.autoservice.dto;

import com.itentika.autoservice.domain.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PositionDTO {
	private Long id;
	private String title;

	public PositionDTO(Position position) {
		this.id = position.getId();
		this.title = position.getTitle();
	}
}
