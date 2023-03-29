package com.switchfully.order.domain.repositories.user;

import com.switchfully.order.domain.models.user.UserCredentials;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserCredentialsRepository {
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final Map<UserCredentials, String> userCredentialsDatabase;

    public UserCredentialsRepository(AdminRepository adminRepository, CustomerRepository customerRepository) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        userCredentialsDatabase = new HashMap<>();
        initializeDummyData();
    }

    public void createCredentials(UserCredentials userCredentials, String userId) {
        userCredentialsDatabase.put(userCredentials, userId);
    }

    public UserCredentials getUserCredentials(String username) {
        return userCredentialsDatabase.keySet()
                .stream()
                .filter(credentials -> credentials.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public String getUser(String username) {
        return userCredentialsDatabase.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getUsername().equals(username))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    private void initializeDummyData() {
        userCredentialsDatabase.put(new UserCredentials("lcharles", "pwd"), adminRepository.getByIndex(0));
        userCredentialsDatabase.put(new UserCredentials("jcobol", "pwd"), customerRepository.getCustomerIdByIndex(0));
        userCredentialsDatabase.put(new UserCredentials("mbeck", "pwd"), customerRepository.getCustomerIdByIndex(1));
        userCredentialsDatabase.put(new UserCredentials("mmarques", "pwd"), customerRepository.getCustomerIdByIndex(2));
    }


}
