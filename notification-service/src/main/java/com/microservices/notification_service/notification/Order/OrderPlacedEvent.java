package com.microservices.notification_service.notification.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {
    private String orderNumber;
    private String email;

    public Object getFirstName() {
        return null;
    }

    public Object getLastName() {
        return null;
    }

    public String getEmail() {
        return email;
    }
    public String getOrderNumber() {
        return orderNumber;
    }
}
