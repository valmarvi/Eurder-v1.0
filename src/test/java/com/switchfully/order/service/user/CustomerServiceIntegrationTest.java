package com.switchfully.order.service.user;

import com.switchfully.order.domain.models.user.Address;
import com.switchfully.order.domain.models.user.Customer;
import com.switchfully.order.domain.models.user.UserCredentials;
import com.switchfully.order.domain.repositories.user.AdminRepository;
import com.switchfully.order.domain.repositories.user.CustomerRepository;
import com.switchfully.order.domain.repositories.user.UserCredentialsRepository;
import com.switchfully.order.service.support.dto.user.CreateCustomerDTO;
import com.switchfully.order.service.support.dto.user.UserCredentialsDTO;
import com.switchfully.order.service.support.mapper.user.CreateCustomerMapper;
import com.switchfully.order.service.support.mapper.user.UserCredentialsMapper;
import com.switchfully.order.service.support.wrapper.CustomerWrapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerServiceIntegrationTest {

    CustomerRepository customerRepository;
    UserCredentialsRepository userCredentialsRepository;
    CreateCustomerMapper createCustomerMapper = new CreateCustomerMapper();
    UserCredentialsMapper userCredentialsMapper = new UserCredentialsMapper();

    CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO.CreateCustomerDTOBuilder()
            .withFirstName("Roger")
            .withLastName("Mark")
            .withAddress(new Address("Loud Lane", "50", "1000", "Brussels"))
            .withEmail("r.marck@gmail.com")
            .withPhoneNumber("0678952214")
            .build();

    UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO("rmark", "pwd");

    CustomerWrapper customerWrapper = new CustomerWrapper(createCustomerDTO, userCredentialsDTO);

    Customer customer = createCustomerMapper.toCustomer(customerWrapper.getCreateCustomerDTO());

    UserCredentials customerCredentials = userCredentialsMapper
            .toUserCredentials(customerWrapper.getUserCredentials());

    @BeforeEach
    void init() {
        this.customerRepository = new CustomerRepository();
        this.userCredentialsRepository = new UserCredentialsRepository(new AdminRepository(), new CustomerRepository());
        userCredentialsRepository.createCredentials(customerCredentials, customer.getId());
        customerRepository.createCustomer(customer);
    }

    @Test
    void createCustomer() {
        //Then
        Assertions.assertThat(customerRepository.getAllCustomers()).contains(customer);
    }

    @Test
    void getAllCustomers() {
        //Then
        Assertions.assertThat(customerRepository.getAllCustomers().size()).isEqualTo(4);
    }

    @Test
    void getCustomerById() {
        //When
        String id = customer.getId();

        //Then
        Assertions.assertThat(customerRepository.getCustomerById(id).get()).isEqualTo(customer);
    }
}