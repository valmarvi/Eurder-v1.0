package com.switchfully.order.domain.models.users;

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

    public String getStreetaName() {
        return streetaName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }
}
