package com.switchfully.order.api.order;

import com.switchfully.order.service.order.OrderService;
import com.switchfully.order.service.support.dto.order.OrderDTO;
import com.switchfully.order.service.support.mapper.order.OrderMapper;
import com.switchfully.order.service.support.wrapper.OrderDTOWrapper;
import com.switchfully.order.service.support.wrapper.OrderWrapper;
import com.switchfully.order.service.user.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.switchfully.order.domain.models.user.Feature.CAN_ORDER_ITEMS;

@RestController
@RequestMapping(value = "orders")
public class OrderController {
    private final OrderService orderService;
    private final SecurityService securityService;
    private final Logger myLogger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public OrderDTOWrapper createOrder(@RequestBody OrderWrapper orderWrapper,
                                       @RequestHeader(required = false) String authorization){
        myLogger.info("Adding a New Order to the Database.");
        securityService.validateUser(authorization, CAN_ORDER_ITEMS);
        return orderService.createOrder(orderWrapper);
    }
}
