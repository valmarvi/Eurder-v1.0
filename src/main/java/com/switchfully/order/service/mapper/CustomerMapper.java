package com.switchfully.order.service.mapper;

import com.switchfully.order.domain.models.Customer;
import com.switchfully.order.service.dto.CreateCustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

public Customer toCustomer(CreateCustomerDTO createCustomerDTO){
    return new Customer.CustomerBuilder()
            .withFirstName(createCustomerDTO.getFirstName())
            .withLastName(createCustomerDTO.getLastName())
            .withEmail(createCustomerDTO.getEmail())
            .withAddress(createCustomerDTO.getAddress())
            .withPhoneNumber(createCustomerDTO.getPhoneNumber())
            .build();
}

}
