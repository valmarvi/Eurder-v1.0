package com.switchfully.order.domain.models;

public class Address {
    private final String streetaName;
    private final String houseNumber;
    private final String postCode;
    private final String city;

    public Address(String streetaName, String houseNumber, String postCode, String city) {
        this.streetaName = streetaName;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
        this.city = city;
    }
}
