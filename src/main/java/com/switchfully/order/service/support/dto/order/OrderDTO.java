package com.switchfully.order.service.support.dto.order;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonPropertyOrder({ "itemGroupDTOList", "totalPrice"})
public class OrderDTO {
    private final List<ItemGroupDTO> itemGroupDTOList;
    private final double totalPrice;

    public OrderDTO(List<ItemGroupDTO> itemGroupList) {
        this.itemGroupDTOList = itemGroupList;
        this.totalPrice = calculateTotalPrice(itemGroupList);
    }

    public List<ItemGroupDTO> getItemGroupList() {
        return itemGroupDTOList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice(List<ItemGroupDTO> itemGroupList) {
        return itemGroupList.stream().mapToDouble(ItemGroupDTO::getPrice).sum();
    }
}
