package com.switchfully.order.service.mapper;

import com.switchfully.order.domain.models.Item;
import com.switchfully.order.domain.models.UserCredentials;
import com.switchfully.order.service.dto.CreateItemDTO;
import com.switchfully.order.service.dto.UserCredentialsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {
    //Item -> ItemDTO
    public CreateItemDTO toCreateItemDTO(Item item) {
        return new CreateItemDTO(item.getName(), item.getDescription(), item.getPrice(), item.getStockAmount());
    }

    public Item toItem(CreateItemDTO createItemDTO){
        return new Item(createItemDTO.getName(), createItemDTO.getDescription(), createItemDTO.getPrice(),
                createItemDTO.getStockAmount());
    }
}
