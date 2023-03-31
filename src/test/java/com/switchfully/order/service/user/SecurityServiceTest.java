package com.switchfully.order.service.user;

import com.switchfully.order.domain.models.user.Feature;
import com.switchfully.order.domain.repositories.user.UserCredentialsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SecurityServiceTest {

    @Autowired
    SecurityService securityService;
    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Test
    void validateAuthorization() {
        //Given
        String authorizationNull = null;
        String authorizationBlank = "";

        //When  //Then
        Assertions.assertThatThrownBy(() -> securityService.validateUser(authorizationNull, Feature.CAN_ORDER_ITEMS))
                .hasMessage("The authorization fields must be filled in");
        Assertions.assertThatThrownBy(() -> securityService.validateUser(authorizationBlank, Feature.CAN_ORDER_ITEMS))
                .hasMessage("The authorization fields must be filled in");
    }

    @Test
    void validatePassword() {
        //Given
        String username = "lcharles";
        String password = "pwds";
        String authorizationWithInvalidPassword = "Basic bGNoYXJsZXM6cHdkcw==";

        //When  //Then
        Assertions.assertThatThrownBy(() -> securityService.validateUser(authorizationWithInvalidPassword, Feature.CAN_CREATE_ITEM))
                .hasMessage("Password does not match for user " + username);
    }
}