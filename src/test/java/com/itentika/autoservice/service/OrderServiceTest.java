package com.itentika.autoservice.service;

import com.itentika.autoservice.dao.OrderRepository;
import com.itentika.autoservice.domain.Order;
import com.itentika.autoservice.dto.ClientDTO;
import com.itentika.autoservice.dto.EmployeeDTO;
import com.itentika.autoservice.dto.OrderDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertThrows;

@SpringBootTest()
@Transactional
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void testCreateOrder_success() {
        ClientDTO clientDTO = new ClientDTO();
        ReflectionTestUtils.setField(clientDTO, "name", "Alex");

        EmployeeDTO admin = new EmployeeDTO();
        ReflectionTestUtils.setField(admin, "id", 1L);

        OrderDTO orderDTO = new OrderDTO();
        ReflectionTestUtils.setField(orderDTO, "reason", "Test reason");
        ReflectionTestUtils.setField(orderDTO, "client", clientDTO);
        ReflectionTestUtils.setField(orderDTO, "administrator", admin);

        orderDTO = orderService.createOrder(orderDTO);
        Assertions.assertNotNull(orderDTO.getId());

        Order order = orderRepository
            .findById(orderDTO.getId())
            .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Assertions.assertEquals(orderDTO.getReason(), order.getReason());
        Assertions.assertEquals(orderDTO.getClient().getName(), order.getClient().getName());
        Assertions.assertEquals(orderDTO.getAdministrator().getId(), order.getAdministrator().getId());
    }

    @Test
    void testAcceptOrderNonexistent_throwsException() {
        OrderDTO orderDTO = new OrderDTO();
        ReflectionTestUtils.setField(orderDTO, "id", 1L);
        assertThrows("Order not found", EntityNotFoundException.class, () -> {
            orderService.acceptOrder(orderDTO);
        });
    }
}
