package com.review53.dto;

public class DeliveryRequestDto {
    private String customerName;

    // constructor
    public DeliveryRequestDto() {}
    public DeliveryRequestDto(String customerName) { this.customerName = customerName; }

    // getter
    public String getCustomerName() { return customerName; }

    // setter
    public void setCustomerName(String customerName) { this.customerName = customerName; }
}