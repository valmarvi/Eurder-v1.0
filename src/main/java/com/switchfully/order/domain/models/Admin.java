package com.switchfully.order.domain.models;

import java.util.List;

public class Admin extends User{

    public Admin(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        featureList = List.of();
    }

    @Override
    public boolean canHaveAccessTo(Feature feature) {
        return super.canHaveAccessTo(feature);
    }
}
