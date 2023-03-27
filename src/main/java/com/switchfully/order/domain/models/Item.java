package com.switchfully.order.domain.models;

import java.util.UUID;

public class Item {
    private final String id;
    private final String name;
    private final String description;
    private final int price;
    private final int stockAmount;

    public Item(String name, String description, int price, int stockAmount) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
    }
}
