package com.switchfully.order.domain.models.users;

import java.util.List;

import static com.switchfully.order.domain.models.users.Feature.CAN_CREATE_ITEM;

public class Admin extends User{

    public Admin(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        featureList = List.of(CAN_CREATE_ITEM);
    }

    @Override
    public boolean canHaveAccessTo(Feature feature) {
        return super.canHaveAccessTo(feature);
    }
}
