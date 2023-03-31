package com.switchfully.order.api.order;

import com.switchfully.order.domain.repositories.order.ItemRepository;
import com.switchfully.order.service.support.dto.order.CreateItemDTO;
import com.switchfully.order.service.support.mapper.order.CreateItemMapper;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {

    @LocalServerPort
    int port;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CreateItemMapper createItemMapper;

    @Test
    @DisplayName("When Creating a Item, then it Returns a Created Http Status Code")
    void createItem() {
        //Given
        CreateItemDTO createItemDTO = new CreateItemDTO("iPad Mini", "The best solution for compact",
                450, 5);
        itemRepository.createItem(createItemMapper.toItem(createItemDTO));

        //When  //Then
        RestAssured
                .given()
                .header("Authorization", "Basic bGNoYXJsZXM6cHdk")
                .body(createItemDTO)
                .contentType(JSON)
                .port(port)
                .when()
                .post("items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        //Then
        Assertions.assertThat(itemRepository.getAllItems(Optional.empty()).size()).isEqualTo(7);
    }

    @Test
    void getAllItems() {
        //When  //Then
        RestAssured
                .given()
                .auth().preemptive().basic("lcharles","pwd")
                .contentType(JSON)
                .accept(JSON)
                .port(port)
                .when()
                .get("items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }
}