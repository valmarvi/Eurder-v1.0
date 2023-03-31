package com.switchfully.order.domain.repositories.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemGroupRepositoryTest {

    @Autowired
    ItemGroupRepository itemGroupRepository;

    @Test
    @DisplayName("When Trying to Create a Item Group with a Item that Doen't Exist, Throw an Exception")
    void createItemGroup() {
        //When  //Then
        Assertions.assertThatThrownBy(() -> itemGroupRepository.createItemGroup("a", 5))
                .hasMessage("No Item found with the specified ID");
    }
}