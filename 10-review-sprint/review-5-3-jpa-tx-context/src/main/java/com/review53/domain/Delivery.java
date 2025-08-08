package com.review53.domain;

import com.review53.enums.DeliveryStatus;
import jakarta.persistence.*;

@Entity
@Table(name="deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="delivery_id")
    private Long id;

    @Column(name="customer_name")
    private String customerName;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    // constructor
    public Delivery() {}
    public Delivery(String customerName) {
        this.customerName = customerName;
        this.status = DeliveryStatus.READY;
    }

    // getter
    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public DeliveryStatus getStatus() { return status; }

    // setter
    public void setId(Long id) { this.id = id; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setStatus(DeliveryStatus status) { this.status = status; }

    public void startDelivery() {
        this.status = DeliveryStatus.IN_PROGRESS;
    }

    public void completeDelivery() {
        this.status = DeliveryStatus.COMPLETED;
    }

    public void failDelivery() {
        this.status = DeliveryStatus.EXCEPTION;
    }

    public void updateStatus(DeliveryStatus status) {
        this.status = status;
    }
}