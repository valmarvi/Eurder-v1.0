package com.switchfully.order.domain.models.user;

import com.switchfully.order.domain.models.order.Order;

import java.util.ArrayList;
import java.util.List;

import static com.switchfully.order.domain.models.user.Feature.*;

public class Customer extends User{
    private final Address address;
    private final String phoneNumber;
//    private List<Order> orderList;

    private Customer(String firstName, String lastName, String email, Address address, String phoneNumber) {
        super(firstName, lastName, email);
        featureList = List.of(CAN_ORDER_ITEMS, CAN_RETRIEVE_ALL_ITEMS, CAN_RETRIEVE_ORDERS);
        this.address = address;
        this.phoneNumber = phoneNumber;
//        this.orderList = new ArrayList<>();
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

//    public void addOrder(Order order) {
//        orderList.add(order);
//    }
//
//    public List<Order> getOrderList() {
//        return orderList;
//    }

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
