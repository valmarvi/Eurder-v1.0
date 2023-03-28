package com.switchfully.order.domain.models.order;

import java.time.LocalDate;

public class ItemGroup {
    private final String itemId;
    private final LocalDate shippingDate;
    private final int amount;
    private final int price;

    public ItemGroup(String itemId, int amount, int stockAmount, int price) {
        this.itemId = itemId;
        this.shippingDate = setShippingDate(stockAmount);
        this.amount = amount;
        this.price = price;
    }

    private LocalDate setShippingDate(int stockAmount) {
        if (this.amount >= stockAmount) {
            return LocalDate.now().plusDays(1);
        }

        return LocalDate.now().plusDays(7);
    }
}
