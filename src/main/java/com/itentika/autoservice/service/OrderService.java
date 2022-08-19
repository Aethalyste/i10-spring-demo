package com.itentika.autoservice.service;

import com.itentika.autoservice.dao.ClientRepository;
import com.itentika.autoservice.dao.OrderRepository;
import com.itentika.autoservice.domain.Client;
import com.itentika.autoservice.domain.Order;
import com.itentika.autoservice.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    public OrderService(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }
    public OrderDTO createOrder(OrderDTO orderDTO) {
        // if order already exists - throw error
        Client client = new Client(orderDTO.getClient());
        Order order = new Order(orderDTO, client);
        clientRepository.save(client);
        orderRepository.save(order);

        orderRepository.flush();

        orderDTO.setId(order.getId());
        orderDTO.getClient().setId(client.getId());

        return new OrderDTO();
    }
}
