package com.switchfully.order.api.user;

import com.switchfully.order.domain.models.user.Address;
import com.switchfully.order.domain.models.user.Customer;
import com.switchfully.order.domain.models.user.UserCredentials;
import com.switchfully.order.domain.repositories.user.CustomerRepository;
import com.switchfully.order.domain.repositories.user.UserCredentialsRepository;
import com.switchfully.order.service.support.dto.user.CreateCustomerDTO;
import com.switchfully.order.service.support.dto.user.CustomerDTO;
import com.switchfully.order.service.support.dto.user.UserCredentialsDTO;
import com.switchfully.order.service.support.wrapper.CustomerWrapper;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Test
    @DisplayName("When Creating a Customer, then it Returns a Created Http Status Code")
    void createCustomer() {
        //Given
        CustomerWrapper customerWrapper = new CustomerWrapper(
                new CreateCustomerDTO.CreateCustomerDTOBuilder()
                        .withFirstName("Roger")
                        .withLastName("Marck")
                        .withAddress(new Address("Loud Lane", "50", "1000", "Brussels"))
                        .withEmail("r.marck@gmail.com")
                        .withPhoneNumber("0678952214")
                        .build(),
                new UserCredentialsDTO("rmarck", "pwd")
        );

        //When //Then
        RestAssured.given()
                .body(customerWrapper)
                .contentType(JSON)
                .port(port)
                .when()
                .post("customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("When Fetching All Customers, then it Returns a Ok Http Status Code")
    void getAllCustomers() {
        //When
        CustomerDTO[] retrieveCustomers = RestAssured.given()
                .header("Authorization", "Basic bGNoYXJsZXM6cHdk")
                .accept(JSON)
                .contentType(JSON)
                .port(port)
                .when()
                .get("customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(CustomerDTO[].class);

        //Then
        Assertions.assertThat(retrieveCustomers)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    @DisplayName("When Fetching a Custumer by Id, then Return a Ok Http Status Code")
    void getCustomerById() {
        //Given
        Customer customer = new Customer.CustomerBuilder()
                .withFirstName("Robert")
                .withLastName("Strauss")
                .withAddress(new Address("Locomotion Cross", "30", "9000", "Gent"))
                .withEmail("r.strauss@gmail.com")
                .withPhoneNumber("0248775517")
                .build();

        customerRepository.createCustomer(customer);
        userCredentialsRepository.createCredentials(new UserCredentials("rstrauss", "pwd"), customer.getId());

        //When
        CustomerDTO retrieveCustomerById = RestAssured.given()
                .header("Authorization", "Basic bGNoYXJsZXM6cHdk")
                .accept(JSON)
                .contentType(JSON)
                .port(port)
                .when()
                .get("customers/" + customer.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(CustomerDTO.class);

        //Then
        Assertions.assertThat(retrieveCustomerById.getId()).isEqualTo(customer.getId());
    }
}