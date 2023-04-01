package com.switchfully.order.domain.models.user;

import java.util.List;

import static com.switchfully.order.domain.models.user.Feature.*;

public class Admin extends User{

    public Admin(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        featureList = List.of(CAN_CREATE_ITEM, CAN_RETRIEVE_ALL_CUSTOMERS, CAN_RETRIEVE_ALL_ITEMS, CAN_UPDATE_ITEMS,
                CAN_RETRIEVE_ORDER_THAT_ARE_SHIPPED_TOMORROW);
    }

    @Override
    public boolean canHaveAccessTo(Feature feature) {
        return super.canHaveAccessTo(feature);
    }
}
