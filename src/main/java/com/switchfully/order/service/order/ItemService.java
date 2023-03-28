package com.switchfully.order.service.order;

import com.switchfully.order.domain.models.order.Item;
import com.switchfully.order.domain.repositories.order.ItemRepository;
import com.switchfully.order.service.support.dto.order.CreateItemDTO;
import com.switchfully.order.service.support.dto.order.ItemDTO;
import com.switchfully.order.service.support.mapper.order.CreateItemMapper;
import com.switchfully.order.service.support.mapper.order.ItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CreateItemMapper createItemMapper;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, CreateItemMapper itemMapper, ItemMapper itemMapper1) {
        this.itemRepository = itemRepository;
        this.createItemMapper = itemMapper;
        this.itemMapper = itemMapper1;
    }

    public void createItem(CreateItemDTO createItemDTO) {
        validateItem(createItemDTO);
        Item item = createItemMapper.toCreateItem(createItemDTO);
        itemRepository.createItem(item);
    }

    public List<ItemDTO> getAllItems() {
        return itemMapper.toItemDTOList(itemRepository.getAllItems());
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
