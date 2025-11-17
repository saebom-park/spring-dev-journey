package com.review53.controller;

import com.review53.service.DeliveryService;
import com.review53.dto.DeliveryRequestDto;
import com.review53.dto.DeliveryResponseDto;
import com.review53.dto.DeliveryStatusUpdateDto;
import com.review53.dto.DeliveryStatusResponseDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    // constructor
    public DeliveryController(DeliveryService deliveryService) { this.deliveryService = deliveryService; }

    @PostMapping
    public DeliveryResponseDto createDelivery(@RequestBody DeliveryRequestDto requestDto) {
        return deliveryService.createDelivery(requestDto);
    }

    @PostMapping("/status")
    public DeliveryStatusResponseDto updateStatus(@RequestBody DeliveryStatusUpdateDto statusUpdateDto) {
        return deliveryService.updateStatus(statusUpdateDto);
    }

    @PostMapping("/flush-fail")
    public void flushAndFail(@RequestBody DeliveryRequestDto requestDto) {
        deliveryService.flushAndFail(requestDto);
    }

    @PostMapping("bulk-fail")
    public void bulkFail(@RequestBody List<DeliveryRequestDto> requestDtoList) {
        deliveryService.bulkProcessWithRollback(requestDtoList);
    }
}