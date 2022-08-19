package com.itentika.autoservice.service;

import com.itentika.autoservice.dao.ClientRepository;
import com.itentika.autoservice.domain.Client;
import com.itentika.autoservice.dto.ClientDTO;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	private final ClientRepository clientRepository;

	public ClientService(
		ClientRepository clientRepository
	) {
		this.clientRepository = clientRepository;
	}
}
