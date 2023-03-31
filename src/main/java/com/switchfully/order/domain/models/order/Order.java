package com.switchfully.order.domain.models.order;

import com.switchfully.order.domain.models.user.Customer;

import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final List<ItemGroup> itemGroupList;
    private final double totalPrice;

    public Order(List<ItemGroup> itemGroupList) {
        this.id = UUID.randomUUID().toString();
        this.itemGroupList = itemGroupList;
        this.totalPrice = calculateTotalPrice(itemGroupList);
    }

    public String getId() {
        return id;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream().mapToDouble(itemGroup -> itemGroup.getPrice()).sum();
    }
}
