package com.switchfully.order.service.support.dto.order;

import java.time.LocalDate;

public class ItemGroupDTO {
    private final String itemId;
    private final int amount;

    public ItemGroupDTO(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }
}
