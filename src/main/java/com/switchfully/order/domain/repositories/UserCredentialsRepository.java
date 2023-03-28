package com.switchfully.order.domain.repositories;

import com.switchfully.order.domain.models.UserCredentials;
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

    private void initializeDummyData() {
        userCredentialsDatabase.put(new UserCredentials("lcharles", "pwd"), adminRepository.getByIndex(0));
        userCredentialsDatabase.put(new UserCredentials("jcobol", "pwd"), customerRepository.getByIndex(0));
        userCredentialsDatabase.put(new UserCredentials("mbeck", "pwd"), customerRepository.getByIndex(1));
        userCredentialsDatabase.put(new UserCredentials("mmarques", "pwd"), customerRepository.getByIndex(2));
    }


}
