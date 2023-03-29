package com.switchfully.order.domain.models.order;

import java.time.LocalDate;

public class ItemGroup {
    private final String itemId;
    private final LocalDate shippingDate;
    private final int amount;
    private final double price;

    public ItemGroup(String itemId, int amount, int stockAmount, double price) {
        this.itemId = itemId;
        this.shippingDate = setShippingDate(stockAmount, amount);
        this.amount = amount;
        this.price = price;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    private LocalDate setShippingDate(int stockAmount, int amount) {
        if (amount >= stockAmount) {
            return LocalDate.now().plusDays(7);
        }

        return LocalDate.now().plusDays(1);
    }
}
