package com.switchfully.order.service.dto;

import java.util.UUID;

public class CreateItemDTO {
    private final String id;
    private final String name;
    private final String description;
    private final int price;
    private final int stockAmount;

    public CreateItemDTO(String name, String description, int price, int stockAmount) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getStockAmount() {
        return stockAmount;
    }
}

