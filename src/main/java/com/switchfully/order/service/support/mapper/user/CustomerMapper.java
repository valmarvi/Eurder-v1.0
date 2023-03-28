package com.switchfully.order.service.support.mapper.user;

import com.switchfully.order.domain.models.user.Customer;
import com.switchfully.order.service.support.dto.user.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerDTO customerDTO) {
        return new Customer.CustomerBuilder()
                .withFirstName(customerDTO.getFirstName())
                .withLastName(customerDTO.getLastName())
                .withEmail(customerDTO.getEmail())
                .withAddress(customerDTO.getAddress())
                .withPhoneNumber(customerDTO.getPhoneNumber())
                .build();
    }

    public CustomerDTO toCustomerDTO(Customer customer) {
        return new CustomerDTO.CustomerDTOBuilder()
                .withId(customer.getId())
                .withFirstName(customer.getFirstName())
                .withLastName(customer.getLastName())
                .withEmail(customer.getEmail())
                .withAddress(customer.getAddress())
                .withPhoneNumber(customer.getPhoneNumber())
                .build();
    }

    public List<CustomerDTO> toCustomerDTOList(List<Customer> customerList) {
                return customerList.stream()
                .map(this::toCustomerDTO)
                .toList();
    }
}
