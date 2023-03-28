package com.switchfully.order.service;

import com.switchfully.order.domain.models.UserCredentials;
import com.switchfully.order.domain.models.users.Feature;
import com.switchfully.order.domain.models.users.User;
import com.switchfully.order.domain.repositories.UserCredentialsRepository;
import com.switchfully.order.service.dto.UserCredentialsDTO;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {

    private final UserCredentialsRepository userCredentialsRepository;

    public SecurityService(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        UserCredentialsDTO usernamePassword = getUsernamePassword(authorization);
        UserCredentials userCredentials = userCredentialsRepository.getUserCredentials(usernamePassword.getUsername());
        User user = userCredentialsRepository.getUser(userCredentials.getUsername());
    }

    private UserCredentialsDTO getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UserCredentialsDTO(username, password);
    }
}
