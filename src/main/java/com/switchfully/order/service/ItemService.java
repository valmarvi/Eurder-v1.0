package com.switchfully.order.service;

import com.switchfully.order.domain.models.Item;
import com.switchfully.order.domain.repositories.ItemRepository;
import com.switchfully.order.service.dto.CreateItemDTO;
import com.switchfully.order.service.mapper.ItemMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public void createItem(CreateItemDTO createItemDTO) {
        validateItem(createItemDTO);
        Item item = itemMapper.toItem(createItemDTO);
        itemRepository.createItem(item);
    }

    private void validateItem(CreateItemDTO createItemDTO) {
        if (createItemDTO == null) {
            throw new IllegalArgumentException("Create Item data must be filled.");
        }
        validateName(createItemDTO.getName());
        validateDescription(createItemDTO.getDescription());
        validatePrice(createItemDTO.getPrice());
        validateStockAmount(createItemDTO.getStockAmount());
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must be filled");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description must be filled");
        }
    }

    private void validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("The price must be greater than 0");
        }
    }

    private void validateStockAmount(int stockAmount) {
        if (stockAmount < 0) {
            throw new IllegalArgumentException("The Stock Amount must be greater than 0");
        }
    }


}
