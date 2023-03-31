package com.switchfully.order.service.support.mapper.order;

import com.switchfully.order.domain.models.order.ItemGroup;
import com.switchfully.order.domain.models.order.Order;
import com.switchfully.order.service.support.dto.order.ItemGroupDTO;
import com.switchfully.order.service.support.dto.order.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    private final ItemGroupMapper itemGroupMapper;

    public OrderMapper(ItemGroupMapper itemGroupMapper) {
        this.itemGroupMapper = itemGroupMapper;
    }

    public OrderDTO toOrderDTO(Order order) {
        List<ItemGroup> itemGroupList = order.getItemGroupList();
        List<ItemGroupDTO> itemGroupDTOList = itemGroupMapper.toItemGroupDTOList(itemGroupList);
        return new OrderDTO(order.getId(), itemGroupDTOList);
    }

    public List<OrderDTO> toOrderListDTO(List<Order> orderList) {
        return orderList
                .stream()
                .map(this::toOrderDTO)
                .toList();
    }
}
