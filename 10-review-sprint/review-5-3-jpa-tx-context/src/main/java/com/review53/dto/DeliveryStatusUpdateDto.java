package com.review53.dto;

import com.review53.enums.DeliveryStatus;

public class DeliveryStatusUpdateDto {
    private Long deliveryId;
    private DeliveryStatus status;

    // constructor
    public DeliveryStatusUpdateDto() {}
    public DeliveryStatusUpdateDto(Long deliveryId, DeliveryStatus status) {
        this.deliveryId = deliveryId;
        this.status = status;
    }

    // getter
    public Long getDeliveryId() { return deliveryId; }
    public DeliveryStatus getStatus() { return status; }

    // setter
    public void setDeliveryId(Long deliveryId) { this.deliveryId = deliveryId; }
    public void setStatus(DeliveryStatus status) { this.status = status; }
}