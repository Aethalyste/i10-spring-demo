package com.itentika.autoservice.dao;

import com.itentika.autoservice.domain.Employee;
import com.itentika.autoservice.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long>  {
	
}
