package com.itentika.autoservice.dao;

import com.itentika.autoservice.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>  {
	
}
