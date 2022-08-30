package com.itentika.autoservice.controllers;

import com.itentika.autoservice.dto.OrderDTO;
import com.itentika.autoservice.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService clientService) {
        this.orderService = clientService;
    }

    @RequestMapping(value="/order",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        orderDTO = orderService.createOrder(orderDTO);

        return ResponseEntity.of(Optional.of(orderDTO));
    }

    @RequestMapping(value="/order/accept",
            method = RequestMethod.PATCH,
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> pickOrder(@RequestBody OrderDTO orderDTO) {
        orderDTO = orderService.acceptOrder(orderDTO);

        return ResponseEntity.of(Optional.of(orderDTO));
    }
}