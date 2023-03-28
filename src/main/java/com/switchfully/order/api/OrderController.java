package com.switchfully.order.api;

import com.switchfully.order.service.OrderService;
import com.switchfully.order.service.dto.ItemGroupDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderController {
    private final OrderService orderService;

    private final Logger myLogger = LoggerFactory.getLogger(ItemController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", value = "")
    public void createOrder(String customerId, List<ItemGroupDTO> itemGroupListDTO){
        myLogger.info("Adding a New Order to the Database.");
        orderService.createOrder(customerId, itemGroupListDTO);
    }
}
