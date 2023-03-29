package com.switchfully.order.service.support.mapper.order;

import com.switchfully.order.domain.models.order.Order;
import com.switchfully.order.service.support.dto.order.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDTO toOrderDTO(Order order) {
        return new OrderDTO(order.getItemGroupList());
    }
}
