package com.switchfully.order.domain.models.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ItemTest {

    @Test
    @DisplayName("Given an Item, Check the Method changeStockAmount()")
    void changeStockAmount() {
        //Given
        Item item = new Item("Test Item", "An Item to Test", 50.5, 10);

        //When
        item.changeStockAmount(5);

        //Then
        Assertions.assertThat(item.getStockAmount()).isEqualTo(5);
    }
}