package com.switchfully.order.service.support.dto.order;

import java.util.List;

public class OrderReportDTO {
    private final List<OrderDTO> orderDTOList;
    private final Double totalOrdersPrice;

    public OrderReportDTO(List<OrderDTO> orderDTOList, Double totalOrdersPrice) {
        this.orderDTOList = orderDTOList;
        this.totalOrdersPrice = totalOrdersPrice;
    }

    public List<OrderDTO> getOrderList() {
        return orderDTOList;
    }

    public Double getTotalOrdersPrice() {
        return totalOrdersPrice;
    }
}
