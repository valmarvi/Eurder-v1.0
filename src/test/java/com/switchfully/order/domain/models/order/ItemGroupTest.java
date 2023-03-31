package com.switchfully.order.domain.models.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ItemGroupTest {

    @Test
    void getItemInStock_whenGettingShippingDate_thenShippingDateIsOneDay() {
        //Given
        Item item = new Item("Test Item", "An Item to Test", 1, 1);

        ItemGroup itemGroup = new ItemGroup(item.getId(), item.getName(), 1, item.getStockAmount(),
                item.getPrice());

        //When
        LocalDate shippingDate = itemGroup.getShippingDate();

        //Then
        Assertions.assertThat(shippingDate).isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    void getItemInStock_whenGettingShippingDate_thenShippingDateIs7Days() {
        //Given
        Item item = new Item("Test Item", "An Item to Test", 1, 0);

        ItemGroup itemGroup = new ItemGroup(item.getId(), item.getName(), 1, item.getStockAmount(),
                item.getPrice());

        //When
        LocalDate shippingDate = itemGroup.getShippingDate();

        //Then
        Assertions.assertThat(shippingDate).isEqualTo(LocalDate.now().plusDays(7));
    }
}