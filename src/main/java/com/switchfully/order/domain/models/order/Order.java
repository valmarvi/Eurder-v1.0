package com.switchfully.order.domain.models.order;

import java.util.List;

public class Order {
    private final List<ItemGroup> itemGroupList;

    public Order(List<ItemGroup> itemGroupList) {
        this.itemGroupList = itemGroupList;
    }
}
