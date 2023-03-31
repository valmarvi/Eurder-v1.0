package com.switchfully.order.service.user;

import com.switchfully.order.domain.models.user.Address;
import com.switchfully.order.service.support.dto.user.CreateCustomerDTO;
import com.switchfully.order.service.support.dto.user.UserCredentialsDTO;
import com.switchfully.order.service.support.wrapper.CustomerWrapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    CreateCustomerDTO.CreateCustomerDTOBuilder createCustomerDTOBuilder =
            new CreateCustomerDTO.CreateCustomerDTOBuilder()
            .withFirstName("Roger")
            .withLastName("Mark")
            .withAddress(new Address("Loud Lane", "50", "1000", "Brussels"))
            .withEmail("r.marck@gmail.com")
            .withPhoneNumber("0678952214");
    UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO("rmark", "pwd");

    @Test
    void validateCustomerWrapper() {
        //When  //Then
        Assertions.assertThatThrownBy(() -> customerService
                        .createCustomer(new CustomerWrapper(null, null)))
                .hasMessage("Admin data and UserCredentials data must be filled.");
    }

    @Test
    void validateFirstName() {
        //Given
        CreateCustomerDTO createCustomerDTOWithNullFirstName = createCustomerDTOBuilder.withFirstName(null).build();
        CreateCustomerDTO createCustomerDTOWithBlankFirstName = createCustomerDTOBuilder.withFirstName("").build();

        CustomerWrapper customerWrapperWithCustomersFirstNameNull =
                new CustomerWrapper(createCustomerDTOWithNullFirstName, userCredentialsDTO);
        CustomerWrapper customerWrapperWithCustomersFirstNameBlank =
                new CustomerWrapper(createCustomerDTOWithBlankFirstName, userCredentialsDTO);

        //When  //Then
        Assertions.assertThatThrownBy(() -> customerService.createCustomer(customerWrapperWithCustomersFirstNameNull))
                .hasMessage("First Name must be filled");
        Assertions.assertThatThrownBy(() -> customerService.createCustomer(customerWrapperWithCustomersFirstNameBlank))
                .hasMessage("First Name must be filled");
    }

    @Test
    void validateLastName() {
        //Given
        CreateCustomerDTO createCustomerDTOWithNullLastName = createCustomerDTOBuilder.withLastName(null).build();
        CreateCustomerDTO createCustomerDTOWithBlankLastName = createCustomerDTOBuilder.withLastName("").build();
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO("rmark", "pwd");

        CustomerWrapper customerWrapperWithCustomersLastNameNull =
                new CustomerWrapper(createCustomerDTOWithNullLastName, userCredentialsDTO);
        CustomerWrapper customerWrapperWithCustomersLastNameBlank =
                new CustomerWrapper(createCustomerDTOWithBlankLastName, userCredentialsDTO);

        //When  //Then
        Assertions.assertThatThrownBy(() -> customerService.createCustomer(customerWrapperWithCustomersLastNameNull))
                .hasMessage("Last Name must be filled");
        Assertions.assertThatThrownBy(() -> customerService.createCustomer(customerWrapperWithCustomersLastNameBlank))
                .hasMessage("Last Name must be filled");
    }

    @Test
    void validateMailNullAndBlank() {
        //Given
        CreateCustomerDTO createCustomerDTOWithNullMail = createCustomerDTOBuilder.withEmail(null).build();
        CreateCustomerDTO createCustomerDTOWithBlankMail = createCustomerDTOBuilder.withEmail("").build();

        CustomerWrapper customerWrapperWithCustomersMailNull =
                new CustomerWrapper(createCustomerDTOWithNullMail, userCredentialsDTO);
        CustomerWrapper customerWrapperWithCustomersMailBlank =
                new CustomerWrapper(createCustomerDTOWithBlankMail, userCredentialsDTO);

        //When  //Then
        Assertions.assertThatThrownBy(() -> customerService.createCustomer(customerWrapperWithCustomersMailNull))
                .hasMessage("e-mail must be filled");
        Assertions.assertThatThrownBy(() -> customerService.createCustomer(customerWrapperWithCustomersMailBlank))
                .hasMessage("e-mail must be filled");
    }

    @Test
    void validateMailFormat() {
        //Given
        CreateCustomerDTO createCustomerDTOWithoutAtSymbolInEmail =
                createCustomerDTOBuilder.withEmail("r.markgmail.com").build();
        CreateCustomerDTO createCustomerDTOWithPointBeforeExtension =
                createCustomerDTOBuilder.withEmail("r.mark@gmailcom").build();

        CustomerWrapper customerWrapperWithCustomersMailWithoutAtSymbol =
                new CustomerWrapper(createCustomerDTOWithoutAtSymbolInEmail, userCredentialsDTO);
        CustomerWrapper customerWrapperWithCustomersMailWithPointBeforeExtension =
                new CustomerWrapper(createCustomerDTOWithPointBeforeExtension, userCredentialsDTO);

        //When  //Then
        Assertions.assertThatThrownBy(
                () -> customerService.createCustomer(customerWrapperWithCustomersMailWithoutAtSymbol))
                .hasMessage("Invalid e-mail");
        Assertions.assertThatThrownBy(
                () -> customerService.createCustomer(customerWrapperWithCustomersMailWithPointBeforeExtension))
                .hasMessage("Invalid e-mail");
    }

    @Test
    void validatePhoneNumber() {
        //Given
        CreateCustomerDTO createCustomerDTOWithNullPhoneNumber = createCustomerDTOBuilder.withPhoneNumber(null).build();
        CreateCustomerDTO createCustomerDTOWithBlankPhoneNumber = createCustomerDTOBuilder.withPhoneNumber("").build();

        CustomerWrapper customerWrapperWithCustomersMailNull =
                new CustomerWrapper(createCustomerDTOWithNullPhoneNumber, userCredentialsDTO);
        CustomerWrapper customerWrapperWithCustomersMailBlank =
                new CustomerWrapper(createCustomerDTOWithBlankPhoneNumber, userCredentialsDTO);

        //When  //Then
        Assertions.assertThatThrownBy(() -> customerService.createCustomer(customerWrapperWithCustomersMailNull))
                .hasMessage("Phone Number must be filled");
        Assertions.assertThatThrownBy(() -> customerService.createCustomer(customerWrapperWithCustomersMailBlank))
                .hasMessage("Phone Number must be filled");
    }

    @Test
    void validateAddress() {
        //Given
        CreateCustomerDTO createCustomerDTOWithNullAddress = createCustomerDTOBuilder.withAddress(null).build();

        CustomerWrapper customerWrapperWithCustomersAddressNull =
                new CustomerWrapper(createCustomerDTOWithNullAddress, userCredentialsDTO);

        //When  //Then
        Assertions.assertThatThrownBy(() -> customerService.createCustomer(customerWrapperWithCustomersAddressNull))
                .hasMessage("Address must be filled");
    }

    @Test
    void validateAddressStreetName() {
        //Given
        CreateCustomerDTO createCustomerDTOWithNullAddressStreetName =
                createCustomerDTOBuilder
                        .withAddress(new Address(null, "50", "1000", "Brussels"))
                        .build();
        CreateCustomerDTO createCustomerDTOWithBlankAddressStreetName =
                createCustomerDTOBuilder
                        .withAddress(new Address("", "50", "1000", "Brussels"))
                        .build();

        CustomerWrapper customerWrapperWithCustomersAddressStreetNameNull =
                new CustomerWrapper(createCustomerDTOWithNullAddressStreetName, userCredentialsDTO);
        CustomerWrapper customerWrapperWithCustomersAddressStreetNameBlank =
                new CustomerWrapper(createCustomerDTOWithBlankAddressStreetName, userCredentialsDTO);

        //When  //Then
        Assertions.assertThatThrownBy(
                () -> customerService.createCustomer(customerWrapperWithCustomersAddressStreetNameNull))
                .hasMessage("Street Name must be filled");
        Assertions.assertThatThrownBy(
                () -> customerService.createCustomer(customerWrapperWithCustomersAddressStreetNameBlank))
                .hasMessage("Street Name must be filled");
    }

    @Test
    void validateAddressHouseNumber() {
        //Given
        CreateCustomerDTO createCustomerDTOWithNullAddressHouseNumber =
                createCustomerDTOBuilder
                        .withAddress(new Address("Loud Lane", null,
                                "1000", "Brussels"))
                        .build();
        CreateCustomerDTO createCustomerDTOWithBlankAddressHouseNumber =
                createCustomerDTOBuilder
                        .withAddress(new Address("Loud Lane", "",
                                "1000", "Brussels"))
                        .build();

        CustomerWrapper customerWrapperWithCustomersAddressHouseNumberNull =
                new CustomerWrapper(createCustomerDTOWithNullAddressHouseNumber, userCredentialsDTO);
        CustomerWrapper customerWrapperWithCustomersAddressHouseNumberBlank =
                new CustomerWrapper(createCustomerDTOWithBlankAddressHouseNumber, userCredentialsDTO);

        //When  //Then
        Assertions.assertThatThrownBy(
                        () -> customerService.createCustomer(customerWrapperWithCustomersAddressHouseNumberNull))
                .hasMessage("House Number must be filled");
        Assertions.assertThatThrownBy(
                        () -> customerService.createCustomer(customerWrapperWithCustomersAddressHouseNumberBlank))
                .hasMessage("House Number must be filled");
    }

    @Test
    void validateAddressCity() {
        //Given
        CreateCustomerDTO createCustomerDTOWithNullAddressCity =
                createCustomerDTOBuilder
                        .withAddress(new Address("Loud Lane", "50",
                                "1000", null))
                        .build();
        CreateCustomerDTO createCustomerDTOWithBlankAddressCity =
                createCustomerDTOBuilder
                        .withAddress(new Address("Loud Lane", "50",
                                "1000", ""))
                        .build();

        CustomerWrapper customerWrapperWithCustomersAddressCityNull =
                new CustomerWrapper(createCustomerDTOWithNullAddressCity, userCredentialsDTO);
        CustomerWrapper customerWrapperWithCustomersAddressCityBlank =
                new CustomerWrapper(createCustomerDTOWithBlankAddressCity, userCredentialsDTO);

        //When  //Then
        Assertions.assertThatThrownBy(
                        () -> customerService.createCustomer(customerWrapperWithCustomersAddressCityNull))
                .hasMessage("City must be filled");
        Assertions.assertThatThrownBy(
                        () -> customerService.createCustomer(customerWrapperWithCustomersAddressCityBlank))
                .hasMessage("City must be filled");
    }

    @Test
    void validateAddressPostCode() {
        //Given
        CreateCustomerDTO createCustomerDTOWithNullAddressPostCode =
                createCustomerDTOBuilder
                        .withAddress(new Address("Loud Lane", "50",
                                null, "Brussels"))
                        .build();
        CreateCustomerDTO createCustomerDTOWithBlankAddressPostCode =
                createCustomerDTOBuilder
                        .withAddress(new Address("Loud Lane", "50",
                                "", "Brussels"))
                        .build();

        CustomerWrapper customerWrapperWithCustomersAddressPostCodeNull =
                new CustomerWrapper(createCustomerDTOWithNullAddressPostCode, userCredentialsDTO);
        CustomerWrapper customerWrapperWithCustomersAddressPostCodeBlank =
                new CustomerWrapper(createCustomerDTOWithBlankAddressPostCode, userCredentialsDTO);

        //When  //Then
        Assertions.assertThatThrownBy(
                        () -> customerService.createCustomer(customerWrapperWithCustomersAddressPostCodeNull))
                .hasMessage("Post Code must be filled");
        Assertions.assertThatThrownBy(
                        () -> customerService.createCustomer(customerWrapperWithCustomersAddressPostCodeBlank))
                .hasMessage("Post Code must be filled");
    }
}