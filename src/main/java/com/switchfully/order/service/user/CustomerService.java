package com.switchfully.order.service.user;

import com.switchfully.order.domain.models.user.Address;
import com.switchfully.order.domain.models.user.Customer;
import com.switchfully.order.domain.models.user.UserCredentials;
import com.switchfully.order.domain.repositories.user.CustomerRepository;
import com.switchfully.order.domain.repositories.user.UserCredentialsRepository;
import com.switchfully.order.service.support.dto.user.CustomerDTO;
import com.switchfully.order.service.support.mapper.user.CreateCustomerMapper;
import com.switchfully.order.service.support.mapper.user.CustomerMapper;
import com.switchfully.order.service.support.mapper.user.UserCredentialsMapper;
import com.switchfully.order.service.support.wrapper.CustomerWrapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final CreateCustomerMapper createCustomerMapper;
    private final UserCredentialsMapper userCredentialsMapper;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, UserCredentialsRepository userCredentialsRepository,
                           CreateCustomerMapper createCustomerMapper, UserCredentialsMapper userCredentialsMapper,
                           CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.createCustomerMapper = createCustomerMapper;
        this.userCredentialsMapper = userCredentialsMapper;
        this.customerMapper = customerMapper;
    }

    public void createCustomer(CustomerWrapper customerWrapper) {
        validateCustomerWrapper(customerWrapper);
        Customer customer = createCustomerMapper.toCustomer(customerWrapper.getCreateCustomerDTO());
        UserCredentials customerCredentials = userCredentialsMapper
                .toUserCredentials(customerWrapper.getUserCredentials());
        userCredentialsRepository.createCredentials(customerCredentials, customer.getId());
        customerRepository.createCustomer(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.toCustomerDTOList(customerRepository.getAllCustomers());
    }

    public CustomerDTO getCustomerById(String customerId) {
        Optional<Customer> customerOptional = customerRepository.getCustomerById(customerId);
        Customer customer = customerOptional
                .orElseThrow(() -> new NotFoundException("No Customer Found with the Provided ID"));
        return customerMapper.toCustomerDTO(customer);
    }

    private void validateCustomerWrapper(CustomerWrapper customerWrapper) {
        validateCustomWrapperNull(customerWrapper);
        validateFirstName(customerWrapper.getCreateCustomerDTO().getFirstName());
        validateLastName(customerWrapper.getCreateCustomerDTO().getLastName());
        validateMail(customerWrapper.getCreateCustomerDTO().getEmail());
        validatePhoneNumber(customerWrapper.getCreateCustomerDTO().getPhoneNumber());
        validateAddress(customerWrapper.getCreateCustomerDTO().getAddress());
    }

    private static void validateCustomWrapperNull(CustomerWrapper customerWrapper) {
        if (customerWrapper.getCreateCustomerDTO() == null || customerWrapper.getUserCredentials() == null) {
            throw new IllegalArgumentException("Admin data and UserCredentials data must be filled.");
        }
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

        if (!(email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))) {
            throw new IllegalArgumentException("Invalid e-mail");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone Number must be filled");
        }
    }

    private void validateAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address must be filled");
        }
        validateAddressStreetName(address.getStreetName());
        validateAddressHouseNumber(address.getHouseNumber());
        validateAddressCity(address.getCity());
        validateAddressPostCode(address.getPostCode());
    }

    private void validateAddressStreetName(String streetName) {
        if (streetName == null || streetName.isBlank()) {
            throw new IllegalArgumentException("Street Name must be filled");
        }
    }

    private void validateAddressHouseNumber(String houseNumber) {
        if (houseNumber == null || houseNumber.isBlank()) {
            throw new IllegalArgumentException("House Number must be filled");
        }
    }

    private void validateAddressCity(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City must be filled");
        }
    }

    private void validateAddressPostCode(String postCode) {
        if (postCode == null || postCode.isBlank()) {
            throw new IllegalArgumentException("Post Code must be filled");
        }
    }
}
