package com.itentika.autoservice.dao;

import com.itentika.autoservice.domain.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>  {
	
}
