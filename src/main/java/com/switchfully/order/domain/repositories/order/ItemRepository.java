package com.switchfully.order.domain.repositories.order;

import com.switchfully.order.domain.models.order.Item;
import com.switchfully.order.service.support.dto.order.ItemDTO;
import com.switchfully.order.service.support.dto.order.UpdateItemDTO;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepository {
    private final List<Item> itemDatabase;

    public ItemRepository() {
        itemDatabase = new ArrayList<>();
        initializeDummyData();
    }

    public void createItem(Item item) {
        itemDatabase.add(item);
    }

    public List<Item> getAllItems(Optional<String> stockUrgencyIndicator) {
        return stockUrgencyIndicator.map(s -> itemDatabase
                .stream()
                .sorted(Comparator.comparing(Item::getStockUrgencyIndicator))
                .toList()
                .stream()
                .filter(item -> item.getStockUrgencyIndicator().toString().equals(s))
                .toList()).orElse(itemDatabase);
    }

    public Item getItemByID(String itemId) {
        return itemDatabase.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(()->new NotFoundException("No Item found with the specified ID"));
    }

    private void initializeDummyData() {
        Item iphone = new Item("iPhone 15", "A high-quality Smartphone", 1500, 50);
        Item ipad = new Item("iPad 2025", "A premium Tablet", 750, 25);
        Item iwatch = new Item("iWatch 10", "A customizable Watch", 500, 10);
        Item airpods = new Item("Air Pods 5", "An advanced Sound Gadget", 250, 5);
        Item macbook = new Item("MacBook 2025", "An all-in-one Laptop", 2500, 2);
        itemDatabase.add(iphone);
        itemDatabase.add(ipad);
        itemDatabase.add(iwatch);
        itemDatabase.add(airpods);
        itemDatabase.add(macbook);
    }
}
