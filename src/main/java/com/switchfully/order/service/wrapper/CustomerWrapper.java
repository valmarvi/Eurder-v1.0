package com.switchfully.order.service.wrapper;

import com.switchfully.order.service.dto.CreateCustomerDTO;
import com.switchfully.order.service.dto.UserCredentialsDTO;

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
