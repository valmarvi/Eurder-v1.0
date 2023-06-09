package com.switchfully.order.service.support.wrapper;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.switchfully.order.service.support.dto.order.OrderDTO;
import com.switchfully.order.service.support.dto.user.CustomerDTO;

public class OrderDTOWrapper {
    @JsonPropertyOrder({ "customerDTO", "orderDTO"})
    private final CustomerDTO customerDTO;
    private final OrderDTO orderDTO;

    public OrderDTOWrapper(CustomerDTO customerDTO, OrderDTO orderDTO) {
        this.customerDTO = customerDTO;
        this.orderDTO = orderDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }
}
