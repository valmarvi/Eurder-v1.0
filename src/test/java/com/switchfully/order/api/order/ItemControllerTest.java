package com.switchfully.order.api.order;

import com.switchfully.order.service.support.dto.order.CreateItemDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {

    @LocalServerPort
    int port;

    @Test
    @DisplayName("When Creating a Item, then it Returns a Created Http Status Code")
    void createItem() {
        //Given
        CreateItemDTO createItemDTO = new CreateItemDTO("iPad Mini", "The best solution for compact",
                450, 5);

        //When  //Then
        RestAssured.given()
                .header("Authorization", "Basic bGNoYXJsZXM6cHdk")
                .body(createItemDTO)
                .contentType(JSON)
                .port(port)
                .when()
                .post("items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }
}