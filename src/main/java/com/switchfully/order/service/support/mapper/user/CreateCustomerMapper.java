package com.switchfully.order.service.support.mapper.user;

import com.switchfully.order.domain.models.user.Customer;
import com.switchfully.order.service.support.dto.user.CreateCustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerMapper {
    public Customer toCustomer(CreateCustomerDTO createCustomerDTO) {
        return new Customer.CustomerBuilder()
                .withFirstName(createCustomerDTO.getFirstName())
                .withLastName(createCustomerDTO.getLastName())
                .withEmail(createCustomerDTO.getEmail())
                .withAddress(createCustomerDTO.getAddress())
                .withPhoneNumber(createCustomerDTO.getPhoneNumber())
                .build();
    }
}
