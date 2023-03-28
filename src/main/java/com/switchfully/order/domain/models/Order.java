package com.switchfully.order.domain.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<ItemGroup> itemGroupList;

    public Order(List<ItemGroup> itemGroupList) {
        this.itemGroupList = itemGroupList;
    }
}
