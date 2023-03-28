package com.switchfully.order.service.order;

import com.switchfully.order.domain.models.order.ItemGroup;
import com.switchfully.order.domain.repositories.order.ItemGroupRepository;
import com.switchfully.order.domain.repositories.order.OrderRepository;
import com.switchfully.order.service.support.dto.order.ItemGroupDTO;
import org.springframework.stereotype.Service;

import java.util.List;

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
