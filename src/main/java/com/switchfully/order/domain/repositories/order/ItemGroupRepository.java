package com.switchfully.order.domain.repositories.order;

import com.switchfully.order.domain.models.order.Item;
import com.switchfully.order.domain.models.order.ItemGroup;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

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
        double itemGroupPrice = calculateItemGroupPrice(amount, item);
        ItemGroup itemGroup = new ItemGroup(item.get().getId(), item.get().getName(),amount, item.get().getStockAmount(), itemGroupPrice);
        item.get().changeStockAmount(amount);
        return itemGroup;
    }

    @ExceptionHandler
    private void validateItem(Optional<Item> item) {
        if (item.isEmpty()) {
            throw new NotFoundException("No Item found with the specified ID");
        }
    }

    private static double calculateItemGroupPrice(int amount, Optional<Item> item) {
        return amount * item.get().getPrice();
    }
}
