package com.itentika.autoservice.controllers;

import com.itentika.autoservice.dto.OrderDTO;
import com.itentika.autoservice.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> saveClientData(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);

        return ResponseEntity.ok().build();
    }
}