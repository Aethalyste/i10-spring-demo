package com.itentika.autoservice.service;

import com.itentika.autoservice.dao.ClientRepository;
import com.itentika.autoservice.dao.OrderHistoryRepository;
import com.itentika.autoservice.dao.OrderRepository;
import com.itentika.autoservice.domain.Client;
import com.itentika.autoservice.domain.Order;
import com.itentika.autoservice.domain.OrderHistory;
import com.itentika.autoservice.domain.OrderStatus;
import com.itentika.autoservice.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    public OrderService(
            ClientRepository clientRepository,
            OrderRepository orderRepository,
            OrderHistoryRepository orderHistoryRepository
    ) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.orderHistoryRepository = orderHistoryRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Client client = new Client(orderDTO.getClient());
        Order order = new Order(orderDTO, client);
        OrderHistory orderHistory = new OrderHistory(order, OrderStatus.NEW, "This is a new order", new Date());

        clientRepository.save(client);
        orderRepository.save(order);
        orderHistoryRepository.save(orderHistory);

        orderRepository.flush();

        return new OrderDTO(order);
    }

    public void pickOrder(OrderDTO orderDTO) {
        Order order = orderRepository.getReferenceById(orderDTO.getId());

    }
}
