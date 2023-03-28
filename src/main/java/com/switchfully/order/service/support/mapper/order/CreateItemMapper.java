package com.switchfully.order.service.support.mapper.order;

import com.switchfully.order.domain.models.order.Item;
import com.switchfully.order.service.support.dto.order.CreateItemDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateItemMapper {
    public Item toCreateItem(CreateItemDTO createItemDTO){
        return new Item(createItemDTO.getName(), createItemDTO.getDescription(), createItemDTO.getPrice(),
                createItemDTO.getStockAmount());
    }
}
