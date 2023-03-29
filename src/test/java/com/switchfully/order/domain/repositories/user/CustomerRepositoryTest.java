package com.switchfully.order.domain.repositories.user;

import com.switchfully.order.domain.models.user.Address;
import com.switchfully.order.domain.models.user.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    Customer anotherCustomerToTest = new Customer.CustomerBuilder()
            .withFirstName("Lars")
            .withLastName("Thyr")
            .withEmail("l.thyr@gmail.com")
            .withAddress(new Address("Squared Square", "5", "1000", "Brussels"))
            .withPhoneNumber("0974485521")
            .build();

    @Test
    @DisplayName("When Creating a Customer, the Repository Must Contain the Create Customer")
    void createCustomer() {
        //When
        customerRepository.createCustomer(customerToTest);

        //Then
        Assertions.assertThat(customerRepository.getAllCustomers()).contains(customerToTest);
    }

    @Test
    @DisplayName("When Retrieving All Customers, the Amount Must Be 3 (Dummy Data)")
    void getAllCustomers() {
        //When
        customerRepository.getAllCustomers();

        //Then
        Assertions.assertThat(customerRepository.getAllCustomers()).hasSize(3);
    }

    @Test
    @DisplayName("When Creating a Customer, This Must Be Retrieved Once its Fetched By It's Id")
    void getCustomerById() {
        //When
        customerRepository.createCustomer(customerToTest);

        //Then
        Assertions.assertThat(customerRepository.getCustomerById(customerToTest.getId())).isPresent();
    }

    @Test
    @DisplayName("When Creating a Customer, This Must Be Retrieved Once its Fetched By It's Index")
    void getByIndex() {
        //When
        customerRepository.createCustomer(customerToTest);

        //Then
        Assertions.assertThat(customerRepository.getCustomerIdByIndex(3)).isEqualTo(customerToTest.getId());
    }

    @Test
    @DisplayName("When Creating Two Customers with the Same e-mail, Throw an Exception on the Creatin of the Second")
    void create2CustomersWithSameEmail() {
        //When //Then
        customerRepository.createCustomer(customerToTest);
        Assertions.assertThatThrownBy(() -> customerRepository.createCustomer(anotherCustomerToTest));
    }
}