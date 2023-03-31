package com.switchfully.order.service.support.wrapper;

import com.switchfully.order.domain.models.order.Order;

import java.util.List;

public class OrderReportWrapper {
    private final List<Order> orderList;
    private final Double totalOrdersPrice;

    public OrderReportWrapper(List<Order> orderList, Double totalOrdersPrice) {
        this.orderList = orderList;
        this.totalOrdersPrice = totalOrdersPrice;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public Double getTotalOrdersPrice() {
        return totalOrdersPrice;
    }
}
