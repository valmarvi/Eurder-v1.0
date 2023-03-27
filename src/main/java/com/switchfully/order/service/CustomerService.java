package com.switchfully.order.service;

import com.switchfully.order.domain.models.Customer;
import com.switchfully.order.domain.models.UserCredentials;
import com.switchfully.order.domain.repositories.CustomerRepository;
import com.switchfully.order.domain.repositories.UserCredentialsRepository;
import com.switchfully.order.service.mapper.CustomerMapper;
import com.switchfully.order.service.mapper.UserCredentialsMapper;
import com.switchfully.order.service.wrapper.CustomerWrapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final CustomerMapper customerMapper;
    private final UserCredentialsMapper userCredentialsMapper;

    public CustomerService(CustomerRepository customerRepository, UserCredentialsRepository userCredentialsRepository,
                           CustomerMapper customerMapper, UserCredentialsMapper userCredentialsMapper) {
        this.customerRepository = customerRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.customerMapper = customerMapper;
        this.userCredentialsMapper = userCredentialsMapper;
    }

    public void createCustomer(CustomerWrapper customerWrapper) {
        validateCustomerWrapper(customerWrapper);
        Customer customer = customerMapper.toCustomer(customerWrapper.getCreateCustomerDTO());
        UserCredentials customerCredentials = userCredentialsMapper
                .toUserCredentials(customerWrapper.getUserCredentials());
        userCredentialsRepository.createCredentials(customerCredentials, customer.getId());
        customerRepository.createCustomer(customer);
    }

    private void validateCustomerWrapper(CustomerWrapper customerWrapper) {
        if(customerWrapper.getCreateCustomerDTO() == null || customerWrapper.getUserCredentials() == null){
            throw new IllegalArgumentException("Admin data and UserCredentials data must be filled.");
        }
        validateMail(customerWrapper.getCreateCustomerDTO().getEmail());
        validateFirstName(customerWrapper.getCreateCustomerDTO().getFirstName());
        validateLastName(customerWrapper.getCreateCustomerDTO().getLastName());
        validateMail(customerWrapper.getCreateCustomerDTO().getEmail());
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First Name must be filled");
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last Name must be filled");
        }
    }

    private void validateMail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("e-mail must be filled");
        }

        if (!(email.matches("^(.+)@(\\S+)$"))) {
            throw new IllegalArgumentException("Invalid e-mail");
        }
    }
}
