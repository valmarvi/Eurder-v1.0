package com.switchfully.order.domain.models.order;

import java.time.LocalDate;

public class ItemGroup {
    private final String itemId;
    private final String itemName;
    private final LocalDate shippingDate;
    private final int amount;
    private final double price;

    public ItemGroup(String itemId, String itemName, int amount, int stockAmount, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.shippingDate = setShippingDate(stockAmount, amount);
        this.amount = amount;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    private LocalDate setShippingDate(int stockAmount, int amount) {
        if (amount > stockAmount) {
            return LocalDate.now().plusDays(7);
        }

        return LocalDate.now().plusDays(1);
    }
}
