package com.switchfully.order.domain.models.user;

import java.util.List;

import static com.switchfully.order.domain.models.user.Feature.CAN_ORDER_ITEMS;
import static com.switchfully.order.domain.models.user.Feature.CAN_RETRIEVE_ALL_ITEMS;

public class Customer extends User{
    private final Address address;
    private final String phoneNumber;

    private Customer(String firstName, String lastName, String email, Address address, String phoneNumber) {
        super(firstName, lastName, email);
        featureList = List.of(CAN_ORDER_ITEMS, CAN_RETRIEVE_ALL_ITEMS);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean canHaveAccessTo(Feature feature) {
        return super.canHaveAccessTo(feature);
    }

    public static class CustomerBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private Address address;
        private String phoneNumber;

        public CustomerBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public CustomerBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Customer build() {
            return new Customer(firstName, lastName, email, address, phoneNumber);
        }
    }
}
