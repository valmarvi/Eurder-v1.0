package com.switchfully.order.service.order;

import com.switchfully.order.domain.models.order.ItemGroup;
import com.switchfully.order.domain.repositories.order.ItemGroupRepository;
import com.switchfully.order.domain.repositories.order.OrderRepository;
import com.switchfully.order.service.support.dto.order.OrderDTO;
import com.switchfully.order.service.support.mapper.order.OrderMapper;
import com.switchfully.order.service.support.wrapper.OrderWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemGroupRepository itemGroupRepository;
    private final OrderMapper orderMapper;


    public OrderService(OrderRepository orderRepository, ItemGroupRepository itemGroupRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.itemGroupRepository = itemGroupRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDTO createOrder(OrderWrapper orderWrapper) {
        List<ItemGroup> itemGroupList = orderWrapper.getItemGroupListDTO().stream()
                .map(itemGroupDTO -> itemGroupRepository.createItemGroup(itemGroupDTO.getItemId(), itemGroupDTO.getAmount()))
                .toList();

        return orderMapper.toOrderDTO(orderRepository.createOrder(orderWrapper.getCustomerId(), itemGroupList));
    }
}
