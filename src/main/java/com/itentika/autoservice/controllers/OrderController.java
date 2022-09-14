package com.itentika.autoservice.controllers;

import com.itentika.autoservice.dto.OrderDTO;
import com.itentika.autoservice.dto.OrderStatusDTO;
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

    // 1. Create order
    @RequestMapping(value="/order",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> create(@RequestBody OrderDTO orderDTO) {
        orderDTO = orderService.createOrder(orderDTO);

        return ResponseEntity.of(Optional.of(orderDTO));
    }

    // 2. Pick order
    @RequestMapping(value="/order/accept",
            method = RequestMethod.PATCH,
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> pick(@RequestBody OrderDTO orderDTO) {
        orderDTO = orderService.acceptOrder(orderDTO);

        return ResponseEntity.of(Optional.of(orderDTO));
    }

    // 3. Add items
    @RequestMapping(value="/order/items",
            method = RequestMethod.PATCH,
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> addItems(@RequestBody OrderDTO OrderDTO) {
        OrderDTO orderDTO = orderService.addItems(OrderDTO);

        return ResponseEntity.of(Optional.of(orderDTO));
    }


    // 4. Change status
    @RequestMapping(value="/order/status",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateStatus(@RequestBody OrderStatusDTO orderStatusDTO) {
        OrderDTO orderDTO = orderService.updateStatus(orderStatusDTO);

        return ResponseEntity.of(Optional.of(orderDTO));
    }

    // 5. Current status
    @RequestMapping(value="/order/status",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getStatus(@RequestBody OrderDTO orderDTO) {
        OrderStatusDTO orderStatusDTO = orderService.getStatus(orderDTO);

        return ResponseEntity.of(Optional.of(orderStatusDTO));
    }

    // 6. Edit order
    @RequestMapping(value="/order",
            method = RequestMethod.PATCH,
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateOrder(@RequestBody OrderDTO orderDTO) {
        orderDTO = orderService.updateOrder(orderDTO);

        return ResponseEntity.of(Optional.of(orderDTO));
    }
}