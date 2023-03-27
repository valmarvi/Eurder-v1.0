package com.switchfully.order.service.dto;

import com.switchfully.order.domain.models.Address;

import java.util.UUID;

public class CreateCustomerDTO {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Address address;
    private final String phoneNumber;

    private CreateCustomerDTO(String firstName, String lastName, String email, Address address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class CreateCustomerDTOBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private Address address;
        private String phoneNumber;

        public CreateCustomerDTOBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CreateCustomerDTOBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CreateCustomerDTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CreateCustomerDTOBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public CreateCustomerDTOBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CreateCustomerDTO build() {
            return new CreateCustomerDTO(firstName, lastName, email, address, phoneNumber);
        }
    }
}

