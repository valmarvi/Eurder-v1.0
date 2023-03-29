package com.switchfully.order.domain.models.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @Test
    void getTotalPrice() {
        //Given
        Item item = new Item("Test Item", "An Item to Test", 1, 10);
        Item anotherItem = new Item("Another Test Item", "Another Item to Test", 1, 10);

        ItemGroup itemGroup = new ItemGroup(item.getId(), item.getName(), 1, item.getStockAmount(),
                item.getPrice());
        ItemGroup anotherItemGroup = new ItemGroup(anotherItem.getId(), anotherItem.getName(), 1,
                anotherItem.getStockAmount(), anotherItem.getPrice());

        List<ItemGroup> itemGroupList = List.of(itemGroup, anotherItemGroup);

        Order order = new Order(itemGroupList);

        //When
        double totalPrice = order.getTotalPrice();

        //Then
        Assertions.assertThat(totalPrice).isEqualTo(2);
    }
}