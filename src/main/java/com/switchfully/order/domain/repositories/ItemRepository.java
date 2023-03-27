package com.switchfully.order.domain.repositories;

import com.switchfully.order.domain.models.Item;

import java.util.concurrent.ConcurrentHashMap;

public class ItemRepository {
    private final ConcurrentHashMap<String, Item> itemDatabase;

    public ItemRepository() {
        itemDatabase = new ConcurrentHashMap<>();
        initializeDummyData();
    }

    private void initializeDummyData() {
        Item iphone = new Item("iPhone 15", "A high-quality Smartphone", 1500, 50);
        Item ipad = new Item("iPad 2025", "A premium Tablet", 750, 25);
        Item iwatch = new Item("iWatch 10", "A customizable Watch", 500, 10);
        Item airpods = new Item("Air Pods 5", "An advanced Sound Gadget", 250, 5);
        Item macbook = new Item("MacBook 2025", "An all-in-one Laptop", 2500, 2);


    }
}
