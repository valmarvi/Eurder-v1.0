package com.switchfully.order.service.user;

import com.switchfully.order.domain.models.user.*;
import com.switchfully.order.domain.repositories.user.AdminRepository;
import com.switchfully.order.domain.repositories.user.CustomerRepository;
import com.switchfully.order.domain.repositories.user.UserCredentialsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.switchfully.order.domain.models.user.Feature.*;

class SecurityServiceIntegrationTest {
    UserCredentialsRepository userCredentialsRepository = new UserCredentialsRepository(new AdminRepository(),
            new CustomerRepository());
    AdminRepository adminRepository = new AdminRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    SecurityService securityService = new SecurityService(userCredentialsRepository, adminRepository,
            customerRepository);
    Admin admin = new Admin("Jack", "Ross", "j.ross@gmail.com");
    Customer customer = new Customer.CustomerBuilder()
            .withFirstName("Roger")
            .withLastName("Mark")
            .withAddress(new Address("Loud Lane", "50", "1000", "Brussels"))
            .withEmail("r.marck@gmail.com")
            .withPhoneNumber("0678952214")
            .build();
    String adminUsername = "jross";
    String customerUsername = "rmark";
    String adminAuthorization = "Basic anJvc3M6cHdk";
    String customerAuthorization = "Basic cm1hcms6cHdk";
    String password = "pwd";

    @Test
    void validateUserAdmin() {
        //Given
        adminRepository.createAdmin(admin);
        userCredentialsRepository.createCredentials(new UserCredentials(adminUsername, password), admin.getId());

        //When
        boolean isUserValidToCreateItems = securityService
                .validateUser(adminAuthorization, CAN_CREATE_ITEM);
        boolean isUserValidToRetrieveAllCustomers = securityService
                .validateUser(adminAuthorization, CAN_RETRIEVE_ALL_CUSTOMERS);
        boolean isUserValidToRetrieveAllItems = securityService
                .validateUser(adminAuthorization, CAN_RETRIEVE_ALL_ITEMS);

        //Then
        Assertions.assertThat(isUserValidToCreateItems).isTrue();
        Assertions.assertThat(isUserValidToRetrieveAllCustomers).isTrue();
        Assertions.assertThat(isUserValidToRetrieveAllItems).isTrue();

        //When  //Then
        Assertions.assertThatThrownBy(() -> securityService
                        .validateUser(adminAuthorization, CAN_ORDER_ITEMS))
                .hasMessage("User " + adminUsername + " does not have access to " + CAN_ORDER_ITEMS);
    }

    @Test
    void validateUserCustomer() {
        //Given
        customerRepository.createCustomer(customer);
        userCredentialsRepository.createCredentials(new UserCredentials(customerUsername, password), customer.getId());

        //When
        boolean isUserValidToCreateItems = securityService
                .validateUser(customerAuthorization, CAN_ORDER_ITEMS);
        boolean isUserValidToRetrieveAllItems = securityService
                .validateUser(customerAuthorization, CAN_RETRIEVE_ALL_ITEMS);

        //Then
        Assertions.assertThat(isUserValidToCreateItems).isTrue();
        Assertions.assertThat(isUserValidToRetrieveAllItems).isTrue();

        //When  //Then
        Assertions.assertThatThrownBy(() -> securityService
                        .validateUser(customerAuthorization, CAN_CREATE_ITEM))
                .hasMessage("User " + customerUsername + " does not have access to " + CAN_CREATE_ITEM);
        Assertions.assertThatThrownBy(() -> securityService
                        .validateUser(customerAuthorization, CAN_RETRIEVE_ALL_CUSTOMERS))
                .hasMessage("User " + customerUsername + " does not have access to " + CAN_RETRIEVE_ALL_CUSTOMERS);
    }
}