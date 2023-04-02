package com.switchfully.order.api.order;

import com.switchfully.order.domain.models.order.Item;
import com.switchfully.order.domain.models.user.Address;
import com.switchfully.order.domain.models.user.Customer;
import com.switchfully.order.domain.models.user.UserCredentials;
import com.switchfully.order.domain.repositories.order.ItemRepository;
import com.switchfully.order.domain.repositories.user.CustomerRepository;
import com.switchfully.order.domain.repositories.user.UserCredentialsRepository;
import com.switchfully.order.service.support.dto.order.ItemGroupDTO;
import com.switchfully.order.service.support.wrapper.OrderWrapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserCredentialsRepository userCredentialsRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("When Creating a Order, then it Returns a Created Http Status Code")
    void createOrder() {
        //Given
        Customer customer = new Customer.CustomerBuilder()
                .withFirstName("Frank")
                .withLastName("Einstein")
                .withAddress(new Address("Test Street", "66", "666", "Forgotten Land"))
                .withEmail("f.einstein@gmail.com")
                .withPhoneNumber("0698774436")
                .build();
        Item charger = new Item("Charger", "Best Solution for Fast Charging", 25, 50);
        Item phoneCase = new Item("Case iPhone 15", "Leather Touch", 75, 25);

        customerRepository.createCustomer(customer);
        userCredentialsRepository.createCredentials(new UserCredentials("feinstein", "pwd"), customer.getId());
        itemRepository.createItem(charger);
        itemRepository.createItem(phoneCase);

        OrderWrapper orderWrapper = new OrderWrapper(customer.getId(), List.of(new ItemGroupDTO(charger.getId(),
                        charger.getName(), 5, charger.getPrice(), charger.getPrice(), LocalDate.now()),
                new ItemGroupDTO(phoneCase.getId(), phoneCase.getName(), 5, phoneCase.getPrice(),
                        phoneCase.getPrice(), LocalDate.now())));

        OrderWrapper anotherOrderWrapper = new OrderWrapper(customer.getId(), List.of(new ItemGroupDTO(charger.getId(),
                        charger.getName(), 5, charger.getPrice(), charger.getPrice(), LocalDate.now()),
                new ItemGroupDTO(phoneCase.getId(), phoneCase.getName(), 5, phoneCase.getPrice(),
                        phoneCase.getPrice(), LocalDate.now())));

        //When  //Then
        RestAssured
                .given()
                .contentType(JSON)
                .auth().preemptive().basic("feinstein","pwd")
                .body(orderWrapper)
                .log().all()
                .when()
                .port(port)
                .post("orders")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        //When  //Then
        RestAssured
                .given()
                .contentType(JSON)
                .auth().preemptive().basic("feinstein","pwd")
                .body(anotherOrderWrapper)
                .log().all()
                .when()
                .port(port)
                .post("orders")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }
}