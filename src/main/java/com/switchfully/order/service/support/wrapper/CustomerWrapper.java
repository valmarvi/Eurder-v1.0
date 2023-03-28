package com.switchfully.order.service.support.wrapper;

import com.switchfully.order.service.support.dto.user.CreateCustomerDTO;
import com.switchfully.order.service.support.dto.user.UserCredentialsDTO;

public class CustomerWrapper {
    private final CreateCustomerDTO createCustomerDTO;
    private final UserCredentialsDTO userCredentials;

    public CustomerWrapper(CreateCustomerDTO createCustomerDTO, UserCredentialsDTO userCredentials){
        this.createCustomerDTO = createCustomerDTO;
        this.userCredentials = userCredentials;
    }

    public CreateCustomerDTO getCreateCustomerDTO() {
        return createCustomerDTO;
    }

    public UserCredentialsDTO getUserCredentials(){
        return userCredentials;
    }
}
