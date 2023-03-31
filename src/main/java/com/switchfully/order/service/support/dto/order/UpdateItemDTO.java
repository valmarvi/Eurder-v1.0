package com.switchfully.order.service.support.dto.order;

public class UpdateItemDTO {
    private final String name;
    private final String description;
    private final double price;
    private final int stockAmount;

    public UpdateItemDTO(String name, String description, double price, int stockAmount) {
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

    public double getPrice() {
        return price;
    }

    public int getStockAmount() {
        return stockAmount;
    }
}
