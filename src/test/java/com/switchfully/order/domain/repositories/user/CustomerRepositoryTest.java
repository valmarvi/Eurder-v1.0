package com.switchfully.order.domain.repositories.user;

import com.switchfully.order.domain.models.user.Address;
import com.switchfully.order.domain.models.user.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    Customer customerToTest = new Customer.CustomerBuilder()
            .withFirstName("Lars")
            .withLastName("Thyr")
            .withEmail("l.thyr@gmail.com")
            .withAddress(new Address("Squared Square", "5", "1000", "Brussels"))
            .withPhoneNumber("0974485521")
            .build();

    @Test
    void createCustomer() {
        //When
        customerRepository.createCustomer(customerToTest);

        //Then
        Assertions.assertThat(customerRepository.getAllCustomers()).contains(customerToTest);
    }

    @Test
    void getAllCustomers() {
        //When
        customerRepository.getAllCustomers();

        //Then
        Assertions.assertThat(customerRepository.getAllCustomers()).hasSize(3);
    }

    @Test
    void getCustomerById() {
        //When
        customerRepository.createCustomer(customerToTest);

        //Then
        Assertions.assertThat(customerRepository.getCustomerById(customerToTest.getId())).isPresent();
    }

    @Test
    void getByIndex() {
        //When
        customerRepository.createCustomer(customerToTest);

        //Then
        Assertions.assertThat(customerRepository.getCustomerIdByIndex(3)).isEqualTo(customerToTest.getId());
    }
}