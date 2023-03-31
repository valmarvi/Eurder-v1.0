package com.switchfully.order.service.support.mapper.user;

import com.switchfully.order.domain.models.user.UserCredentials;
import com.switchfully.order.service.support.dto.user.UserCredentialsDTO;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialsMapper {
    public UserCredentials toUserCredentials(UserCredentialsDTO userCredentialsDTO){
        return new UserCredentials(userCredentialsDTO.getUsername(), userCredentialsDTO.getPassword());
    }
}
