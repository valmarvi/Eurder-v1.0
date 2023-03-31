package com.switchfully.order.domain.repositories.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserCredentialsRepositoryTest {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;
    @Test
    void getUserCredentials() {
        //Given
        String username = "lcharless";

        //When  //Then
        Assertions.assertThatThrownBy(() -> userCredentialsRepository.getUserCredentialsByUsername(username))
                .hasMessage("No Credentials Found for the username " + username);
    }

    @Test
    void getUserByUsername() {
        //Given
        String username = "lcharless";

        //When  //Then
        Assertions.assertThatThrownBy(() -> userCredentialsRepository.getUserByUsername(username))
                .hasMessage("No User Found with the username " + username);
    }
}