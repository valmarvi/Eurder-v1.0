package com.switchfully.order.service.support.dto.user;

import com.switchfully.order.domain.models.user.Address;

public class CustomerDTO {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Address address;
    private final String phoneNumber;

    private CustomerDTO(String id, String firstName, String lastName, String email, Address address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static class CustomerDTOBuilder {
        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private Address address;
        private String phoneNumber;

        public CustomerDTOBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public CustomerDTOBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerDTOBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerDTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerDTOBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public CustomerDTOBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerDTO build() {
            return new CustomerDTO(id, firstName, lastName, email, address, phoneNumber);
        }
    }
}
