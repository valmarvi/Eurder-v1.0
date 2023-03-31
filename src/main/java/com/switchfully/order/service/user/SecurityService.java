package com.switchfully.order.service.user;

import com.switchfully.order.api.order.ItemController;
import com.switchfully.order.domain.models.user.UserCredentials;
import com.switchfully.order.domain.models.user.Feature;
import com.switchfully.order.domain.models.user.User;
import com.switchfully.order.domain.repositories.user.AdminRepository;
import com.switchfully.order.domain.repositories.user.CustomerRepository;
import com.switchfully.order.domain.repositories.user.UserCredentialsRepository;
import com.switchfully.order.exception.exceptions.UnauthorizedAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static java.lang.String.format;

@Service
public class SecurityService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public SecurityService(UserCredentialsRepository userCredentialsRepository, AdminRepository adminRepository,
                           CustomerRepository customerRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
    }

    public boolean validateUser(String authorization, Feature feature) {
        validateAuthorization(authorization);
        String username = getUsernamePassword(authorization).getUsername();
        String password = getUsernamePassword(authorization).getPassword();
        User user = getUserById(userCredentialsRepository.getUser(username));
        validateUser(username, user);
        validatePassword(username, password);
        validateAccess(user, feature, username);
        return true;
    }

    private UserCredentials getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UserCredentials(username, password);
    }

    private User getUserById(String userId) {
        if (adminRepository.getAdminById(userId).isPresent()) {
            return adminRepository.getAdminById(userId).get();
        }
        if (customerRepository.getCustomerById(userId).isPresent()) {
            return customerRepository.getCustomerById(userId).get();
        }

        return null;
    }

    private void validateAuthorization(String authorization) {
        String message;
        if (authorization == null || authorization.isBlank()) {
            message = "The authorization fields must be filled in";
            logger.error(message);
            throw new UnauthorizedAccessException(message);
        }
    }

    private void validateUser(String username, User user) {
        String message;
        if (user == null) {
            message = "There is no user with the username " + username;
            logger.error(message);
            throw new UnauthorizedAccessException(message);
        }
    }

    private void validatePassword(String username, String password) {
        String message;
        if (!password.equals(userCredentialsRepository.getUserCredentials(username).getPassword())) {
            message = "Password does not match for user " + username;
            logger.error(message);
            throw new UnauthorizedAccessException(message);
        }
    }

    private void validateAccess(User user, Feature feature, String username) {
        String message;
        if (!user.canHaveAccessTo(feature)) {
            message = format("User %s does not have access to %s", username, feature);
            logger.error(message);
            throw new UnauthorizedAccessException(message);
        }
    }
}
