package com.itentika.autoservice.service;

import com.itentika.autoservice.dao.*;
import com.itentika.autoservice.domain.*;
import com.itentika.autoservice.dto.OrderDTO;
import com.itentika.autoservice.dto.OrderItemDTO;
import com.itentika.autoservice.dto.OrderStatusDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class OrderService {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemPriceRepository itemPriceRepository;

    public OrderService(
            ClientRepository clientRepository,
            OrderRepository orderRepository,
            EmployeeRepository employeeRepository,
            ItemPriceRepository itemPriceRepository
    ) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;

        this.itemPriceRepository = itemPriceRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Employee administrator = employeeRepository
                .findById(orderDTO.getAdministrator().getId())
                .orElseThrow(() -> new EntityNotFoundException("Administrator not found"));

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
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Employee master = employeeRepository
                .findById(orderDTO.getMaster().getId())
                .orElseThrow(() -> new EntityNotFoundException("Master not found"));

        order.accept(master);

        orderRepository.flush();

        return new OrderDTO(order);
    }

    public OrderStatusDTO getStatus(OrderDTO orderDTO) {
        Order order = orderRepository
                .findById(orderDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        return new OrderStatusDTO(order);
    }

    public OrderDTO updateStatus(OrderStatusDTO orderStatusDTO) {
        Order order = orderRepository
                .findById(orderStatusDTO.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        order.updateStatus(orderStatusDTO.getStatus(), orderStatusDTO.getComment());

        orderRepository.flush();

        return new OrderDTO(order);
    }

    public OrderDTO addItems(OrderDTO orderDTO) {
        Order order = orderRepository
                .findById(orderDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        orderDTO.getOrderItem().forEach((OrderItemDTO orderItemDto) -> {
            ItemPrice itemPrice = itemPriceRepository
                    .findById(orderItemDto.getPriceItem().getId())
                    .orElseThrow(() -> new EntityNotFoundException("ItemPrice not found"));
            new OrderItem(order, itemPrice, orderItemDto.getQuantity());
        });

        orderRepository.flush();

        return new OrderDTO(order);
    }
}
