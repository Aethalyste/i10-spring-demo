package com.itentika.autoservice.dao;

import com.itentika.autoservice.domain.ItemPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPriceRepository extends JpaRepository<ItemPrice, Long>  {
	
}
