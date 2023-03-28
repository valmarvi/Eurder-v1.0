package com.switchfully.order.service.support.mapper.user;

import com.switchfully.order.domain.models.user.UserCredentials;
import com.switchfully.order.service.support.dto.user.UserCredentialsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCredentialsMapper {
    //UserCredentials -> UserCredentialsDTO
    public UserCredentialsDTO toUserCredentialsDTO(UserCredentials userCredentials) {
        return new UserCredentialsDTO(userCredentials.getUsername(), userCredentials.getPassword());
    }

    public UserCredentials toUserCredentials(UserCredentialsDTO userCredentialsDTO){
        return new UserCredentials(userCredentialsDTO.getUsername(), userCredentialsDTO.getPassword());
    }

    //List<UserCredentials> -> List<UserCredentialsDTO>
    public List<UserCredentialsDTO> toUserCredentialsDTO(List<UserCredentials> userCredentialsList) {
        return userCredentialsList.stream()
                .map(this::toUserCredentialsDTO)
                .toList();
    }
}
