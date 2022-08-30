package com.itentika.autoservice.dao;

import com.itentika.autoservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>  {
	
}
