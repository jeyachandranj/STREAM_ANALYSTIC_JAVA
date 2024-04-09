package org.example.event;


import java.io.Serializable;
import java.util.Date;

public class OrderEvent implements Serializable {
    private EventTypeEnum eventType;
    private String orderId;
    private String driverId;
    private String restaurantId;
    private String customerId;
    private Date actionTime;

    public EventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeEnum eventType) {
        this.eventType = eventType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }



    @Override
    public String toString() {
        return "OrderEvent{" +
                "eventType=" + eventType +
                ", orderId='" + orderId + '\'' +
                ", driverId='" + driverId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", actionTime=" + actionTime +

                '}';
    }
}
