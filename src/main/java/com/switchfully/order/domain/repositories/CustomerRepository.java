package com.switchfully.order.domain.repositories;

import com.switchfully.order.domain.models.users.Address;
import com.switchfully.order.domain.models.users.Customer;
import com.switchfully.order.exception.exceptions.DuplicateEmailException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    private final List<Customer> customerDatabase;

    public CustomerRepository() {
        customerDatabase = new ArrayList<>();
        initializeDummyData();
    }

    public void createCustomer(Customer aCustomer) {
        Optional<Customer> memberWithSameEmail = customerDatabase.stream()
                .filter(customer -> customer.getEmail().equals(aCustomer.getEmail()))
                .findAny();

        if (memberWithSameEmail.isPresent()) {
            throw new DuplicateEmailException("There is already a customer with the same e-mail");
        }

        customerDatabase.add(aCustomer);
    }

    public List<Customer> getAllCustomers() {
        return customerDatabase;
    }

    public Optional<Customer> getCustomerByID(String itemId) {
        return customerDatabase.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
    }

    private void initializeDummyData() {
        Customer bigSpender = new Customer.CustomerBuilder()
                .withFirstName("John")
                .withLastName("Cobol")
                .withEmail("john.cobol@gmail.com")
                .withAddress(new Address("High Street", "100", "1000", "Brussels"))
                .withPhoneNumber("0678512297")
                .build();
        Customer mediumSpender = new Customer.CustomerBuilder()
                .withFirstName("Mario")
                .withLastName("Beck")
                .withEmail("mario.beck@gmail.com")
                .withAddress(new Address("Medium Street", "50", "2000", "Antwerp"))
                .withPhoneNumber("0987136874")
                .build();
        Customer lowSpender = new Customer.CustomerBuilder()
                .withFirstName("Maria")
                .withLastName("Marques")
                .withEmail("maria.marques@gmail.com")
                .withAddress(new Address("Low Street", "1", "9000", "Gent"))
                .withPhoneNumber("0978563324")
                .build();

        customerDatabase.add(bigSpender);
        customerDatabase.add(mediumSpender);
        customerDatabase.add(lowSpender);
    }

    public String getByIndex(int index) {
        return customerDatabase.get(index).getId();
    }
}
