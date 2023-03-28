package com.switchfully.order.service.support.wrapper;

import com.switchfully.order.service.support.dto.order.ItemGroupDTO;

import java.util.List;

public class OrderWrapper {
    private final String customerId;
    private final List<ItemGroupDTO> itemGroupListDTO;

    public OrderWrapper(String customerId, List<ItemGroupDTO> itemGroupListDTO) {
        this.customerId = customerId;
        this.itemGroupListDTO = itemGroupListDTO;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ItemGroupDTO> getItemGroupListDTO() {
        return itemGroupListDTO;
    }
}
