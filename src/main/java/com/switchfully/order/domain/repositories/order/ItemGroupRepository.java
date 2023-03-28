package com.switchfully.order.domain.repositories.order;

import com.switchfully.order.domain.models.order.Item;
import com.switchfully.order.domain.models.order.ItemGroup;
import com.switchfully.order.exception.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemGroupRepository {
    private final ItemRepository itemRepository;

    public ItemGroupRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemGroup createItemGroup(String itemId, int amount) {
        Optional<Item> item = itemRepository.getItemByID(itemId);
        validateItem(item);
        int price = amount * item.get().getPrice();
        return new ItemGroup(item.get().getId(), amount, item.get().getStockAmount(), price);
    }

    private void validateItem(Optional<Item> item) {
        if (item.isEmpty()) {
            throw new ItemNotFoundException("No Item found with the specified ID");
        }
    }
}
