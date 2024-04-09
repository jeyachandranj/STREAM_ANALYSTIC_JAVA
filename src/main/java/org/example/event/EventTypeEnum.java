package org.example.event;

import java.io.Serializable;

public enum EventTypeEnum implements Serializable {
    CUSTOMER_ORDER_FOOD,
    DRIVER_ACCEPT,
    PICK_UP_FROM_RESTAURANT,
    DROP_UP_TO_CUSTOMER,
    CUSTOMER_REJECT;

    EventTypeEnum() {
    }
}
