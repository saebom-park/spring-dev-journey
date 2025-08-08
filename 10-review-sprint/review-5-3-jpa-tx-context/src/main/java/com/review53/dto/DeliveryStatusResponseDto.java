package com.review53.dto;
import com.review53.enums.DeliveryStatus;

public class DeliveryStatusResponseDto {
    private Long deliveryId;
    private DeliveryStatus status;
    private String message;

    // constructor
    public DeliveryStatusResponseDto() {}
    public DeliveryStatusResponseDto(Long deliveryId, DeliveryStatus status, String message) {
        this.deliveryId = deliveryId;
        this.status = status;
        this.message = message;
    }

    // getter
    public Long getDeliveryId() { return deliveryId; }
    public DeliveryStatus getStatus() { return status; }
    public String getMessage() { return message; }

    // setter
    public void setDeliveryId(Long deliveryId) { this.deliveryId = deliveryId; }
    public void setStatus(DeliveryStatus status) { this.status = status; }
    public void setMessage(String message) { this.message = message; }
}