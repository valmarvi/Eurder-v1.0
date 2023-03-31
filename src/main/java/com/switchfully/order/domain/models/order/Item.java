package com.switchfully.order.domain.models.order;

import java.util.UUID;

import static com.switchfully.order.domain.models.order.StockUrgencyIndicator.*;

public class Item {
    private final String id;
    private String name;
    private String description;
    private double price;
    private int stockAmount;
    private StockUrgencyIndicator stockUrgencyIndicator;
    private static final int STOCK_LOW_AMOUNT = 5;
    private static final int STOCK_MEDIUM_AMOUNT = 10;
    private static final int STOCK_HIGH_AMOUNT = 10;

    public Item(String name, String description, double price, int stockAmount) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
        this.stockUrgencyIndicator = setStockUrgencyIndicator(stockAmount);
    }

    public StockUrgencyIndicator setStockUrgencyIndicator(int stockAmount) {
        if (stockAmount < STOCK_LOW_AMOUNT) {
            this.stockUrgencyIndicator = STOCK_LOW;
            return STOCK_LOW;
        }

        if (stockAmount < STOCK_MEDIUM_AMOUNT) {
            this.stockUrgencyIndicator = STOCK_MEDIUM;
            return STOCK_MEDIUM;
        }
        this.stockUrgencyIndicator = STOCK_HIGH;
        return STOCK_HIGH;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
        setStockUrgencyIndicator(stockAmount);
    }

    public void changeStockAmount(int amountToSubtract) {
        this.stockAmount -= amountToSubtract;
        setStockUrgencyIndicator(this.stockAmount);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
