package com.switchfully.order.domain.repositories.order;

import com.switchfully.order.domain.repositories.user.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void whenRetrievingACustomerThatDoesNotExist_thenThrowException() {
        //When //Then
        Assertions.assertThatThrownBy(() -> {orderRepository.createOrder("123", null);})
                .hasMessage("No Customer found with the specified ID");
    }
}