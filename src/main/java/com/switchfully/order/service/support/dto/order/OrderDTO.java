package com.switchfully.order.service.support.dto.order;

import com.switchfully.order.domain.models.order.ItemGroup;

import java.util.List;

public class OrderDTO {
    private final List<ItemGroup> itemGroupList;
    private final double totalPrice;

    public OrderDTO(List<ItemGroup> itemGroupList) {
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
