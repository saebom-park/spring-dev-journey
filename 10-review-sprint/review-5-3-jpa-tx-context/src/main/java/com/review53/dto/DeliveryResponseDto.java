package com.review53.dto;

import com.review53.enums.DeliveryStatus;

public class DeliveryResponseDto {
    private Long deliveryId;
    private String customerName;
    private DeliveryStatus status;
    private String message;

    // constructor
    public DeliveryResponseDto() {}
    public DeliveryResponseDto(Long deliveryId, String customerName, DeliveryStatus status, String message) {
        this.deliveryId = deliveryId;
        this.customerName = customerName;
        this.status = status;
        this.message = message;
    }

    // getter
    public Long getDeliveryId() { return deliveryId; }
    public String getCustomerName() { return customerName; }
    public DeliveryStatus getStatus() { return status; }
    public String getMessage() { return message; }

    // setter
    public void setDeliveryId(Long deliveryId) { this.deliveryId = deliveryId; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setStatus(DeliveryStatus status) { this.status = status; }
    public void setMessage(String message) { this.message = message; }
}