package com.switchfully.order.service;

import com.switchfully.order.domain.models.ItemGroup;
import com.switchfully.order.domain.repositories.ItemGroupRepository;
import com.switchfully.order.domain.repositories.OrderRepository;
import com.switchfully.order.service.dto.ItemGroupDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemGroupRepository itemGroupRepository;

    public OrderService(OrderRepository orderRepository, ItemGroupRepository itemGroupRepository) {
        this.orderRepository = orderRepository;
        this.itemGroupRepository = itemGroupRepository;
    }

    public void createOrder(String customerId, List<ItemGroupDTO> itemGroupListDTO) {
        List<ItemGroup> itemGroupList = itemGroupListDTO.stream()
                .map(itemGroupDTO -> itemGroupRepository.createItemGroup(itemGroupDTO.getItemId(), itemGroupDTO.getAmount()))
                .toList();

        orderRepository.createOrder(customerId, itemGroupList);
    }
}
