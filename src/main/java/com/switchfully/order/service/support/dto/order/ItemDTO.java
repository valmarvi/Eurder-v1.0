package com.switchfully.order.service.support.dto.order;

import com.switchfully.order.domain.models.order.StockUrgencyIndicator;

import java.util.UUID;

public class ItemDTO {
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final int stockAmount;
    private StockUrgencyIndicator stockUrgencyIndicator;


    public ItemDTO(String id, String name, String description, double price, int stockAmount, StockUrgencyIndicator stockUrgencyIndicator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
        this.stockUrgencyIndicator = stockUrgencyIndicator;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public StockUrgencyIndicator getStockUrgencyIndicator() {
        return stockUrgencyIndicator;
    }
}
