package com.switchfully.order.domain.models.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserCredentialsTest {

    @Test
    @DisplayName("Given User Credentials, Check the Method doesPasswordMatch()")
    void doesPasswordMatch() {
        //Given
        UserCredentials userCredentials = new UserCredentials("abc", "123");


        //When
        boolean checkIfTrue = userCredentials.doesPasswordMatch("123");
        boolean checkIfFalse = userCredentials.doesPasswordMatch("456");

        //Then
        Assertions.assertThat(checkIfTrue).isTrue();
        Assertions.assertThat(checkIfFalse).isFalse();
    }
}