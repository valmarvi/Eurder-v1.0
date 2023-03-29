package com.switchfully.order.domain.models.order;

import java.util.List;

public class Order {
    private final List<ItemGroup> itemGroupList;
    private final double totalPrice;

    public Order(List<ItemGroup> itemGroupList) {
        this.itemGroupList = itemGroupList;
        this.totalPrice = calculateTotalPrice(itemGroupList);
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream().mapToDouble(ItemGroup::getPrice).sum();
    }
}
