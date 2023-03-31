package com.switchfully.order.service.support.dto.order;

import com.switchfully.order.domain.models.order.Order;
import com.switchfully.order.domain.models.user.Customer;
import com.switchfully.order.service.support.dto.user.CustomerDTO;
import com.switchfully.order.service.support.mapper.order.OrderMapper;
import com.switchfully.order.service.support.mapper.user.CustomerMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrdersByShippingDateMapper {

    private final CustomerMapper customerMapper;
    private final OrderMapper orderMapper;

    public OrdersByShippingDateMapper(CustomerMapper customerMapper, OrderMapper orderMapper) {
        this.customerMapper = customerMapper;
        this.orderMapper = orderMapper;
    }

    public Map<CustomerDTO, List<OrderDTO>> toGetOrderByShippingDateDTO(Map<Customer, List<Order>> getOrderByShippingDate) {
        return getOrderByShippingDate.entrySet()
                .stream()
                .map(entry -> {
                    CustomerDTO customerDTO = customerMapper.toCustomerDTO(entry.getKey());
                    List<OrderDTO> orderDTOList = orderMapper.toOrderListDTO(entry.getValue());
                    return Map.entry(customerDTO, orderDTOList);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
