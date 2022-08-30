package com.itentika.autoservice.service;

import com.itentika.autoservice.dao.ClientRepository;
import com.itentika.autoservice.dao.EmployeeRepository;
import com.itentika.autoservice.dao.OrderHistoryRepository;
import com.itentika.autoservice.dao.OrderRepository;
import com.itentika.autoservice.domain.*;
import com.itentika.autoservice.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;

@Service
public class OrderService {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final EmployeeRepository employeeRepository;

    public OrderService(
            ClientRepository clientRepository,
            OrderRepository orderRepository,
            OrderHistoryRepository orderHistoryRepository,
            EmployeeRepository employeeRepository
    ) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.employeeRepository = employeeRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Employee administrator = employeeRepository
                .findById(orderDTO.getAdministrator().getId())
                .orElseThrow(() -> new InputMismatchException("Administrator not found"));

        Client client = new Client(orderDTO.getClient());
        Order order = new Order(orderDTO, client, administrator);

        clientRepository.save(client);
        orderRepository.save(order);

        orderRepository.flush();

        return new OrderDTO(order);
    }

    public OrderDTO acceptOrder(OrderDTO orderDTO) {
        Order order = orderRepository
                .findById(orderDTO.getId())
                .orElseThrow(() -> new InputMismatchException("Order not found"));

        Employee master = employeeRepository
                .findById(orderDTO.getMaster().getId())
                .orElseThrow(() -> new InputMismatchException("Master not found"));

        order.accept(master);

        orderRepository.flush();

        return new OrderDTO(order);
    }
}
