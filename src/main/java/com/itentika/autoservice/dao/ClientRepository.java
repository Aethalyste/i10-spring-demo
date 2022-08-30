package com.itentika.autoservice.dao;

import com.itentika.autoservice.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>  {
	
}
