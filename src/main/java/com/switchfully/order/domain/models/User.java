package com.switchfully.order.domain.models;

import java.util.List;
import java.util.UUID;

public abstract class User {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    List<Feature> featureList;

    public User(String firstName, String lastName, String email) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public boolean canHaveAccessTo(Feature feature) {
        return featureList.contains(feature);
    }

}
