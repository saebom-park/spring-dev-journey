package com.review53.service;

import com.review53.dto.DeliveryRequestDto;
import com.review53.dto.DeliveryStatusUpdateDto;
import com.review53.dto.DeliveryResponseDto;
import com.review53.dto.DeliveryStatusResponseDto;
import java.util.List;

public interface DeliveryService {
    DeliveryResponseDto createDelivery(DeliveryRequestDto requestDto);
    DeliveryStatusResponseDto updateStatus(DeliveryStatusUpdateDto statusUpdateDto);
    void flushAndFail(DeliveryRequestDto requestDto);
    void bulkProcessWithRollback(List<DeliveryRequestDto> requestDtoList);
}