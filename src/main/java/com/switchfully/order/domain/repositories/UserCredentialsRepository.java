package com.switchfully.order.domain.repositories;

import com.switchfully.order.domain.models.UserCredentials;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserCredentialsRepository {
    private final Map<UserCredentials, String> userCredentialsDatabase;

    public UserCredentialsRepository() {
        userCredentialsDatabase = new HashMap<>();
    }

    public void createCredentials(UserCredentials userCredentials, String userId) {
        userCredentialsDatabase.put(userCredentials, userId);
    }
}
