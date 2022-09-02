package com.itentika.autoservice.service;

import com.itentika.autoservice.dto.OrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertThrows;

@SpringBootTest()
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void testAcceptOrderNonexistent_throwsException() {
        OrderDTO orderDTO = new OrderDTO();
        ReflectionTestUtils.setField(orderDTO, "id", 1L);
        assertThrows("Order not found", EntityNotFoundException.class, () -> {
            orderService.acceptOrder(orderDTO);
        });
    }
}
