package com.switchfully.order.service.support.dto.order;

import java.time.LocalDate;

public class ItemGroupDTO {
    private final String itemId;
    private final String itemName;
    private final int amount;
    private final double unitPrice;
    private final double price;
    private final LocalDate shippingDate;

    public ItemGroupDTO(String itemId, String itemName, int amount, double unitPrice, double price, LocalDate shippingDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.price = price;
        this.shippingDate = shippingDate;
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

    public double getUnitPrice() {
        return unitPrice/amount;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
