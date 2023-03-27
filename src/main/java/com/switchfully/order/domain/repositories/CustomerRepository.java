package com.switchfully.order.domain.repositories;

import com.switchfully.order.domain.models.Address;
import com.switchfully.order.domain.models.Customer;
import com.switchfully.order.exception.exceptions.DuplicatedINSSNumberException;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository {
    private final ConcurrentHashMap<String, Customer> customerDatabase;

    public CustomerRepository() {
        customerDatabase = new ConcurrentHashMap<>();
        initializeDummyData();
    }

    public void createCustomer(Customer aCustomer){
        Optional<Customer> memberWithSameEmail = customerDatabase.values().stream()
                .filter(user -> user.getClass().equals(Customer.class))
                .filter(customer -> customer.getEmail().equals(aCustomer.getEmail()))
                .findAny();

        if (memberWithSameEmail.isPresent()) {
            throw new DuplicatedINSSNumberException("There is already a user with the same e-mail");
        }

        customerDatabase.put(aCustomer.getId(), aCustomer);
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

        customerDatabase.put(bigSpender.getId(), bigSpender);
        customerDatabase.put(mediumSpender.getId(), mediumSpender);
        customerDatabase.put(lowSpender.getId(), lowSpender);
    }
}
