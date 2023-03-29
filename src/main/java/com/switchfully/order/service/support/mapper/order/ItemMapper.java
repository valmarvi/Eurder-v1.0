package com.switchfully.order.service.support.mapper.order;

import com.switchfully.order.domain.models.order.Item;
import com.switchfully.order.service.support.dto.order.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {
    public ItemDTO toItemDTO(Item item) {
        return new ItemDTO(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getStockAmount());
    }

    public List<ItemDTO> toItemDTOList(List<Item> itemList) {
        return itemList.stream()
                .map(this::toItemDTO)
                .toList();
    }
}
