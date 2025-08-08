package com.review53.service;

import com.review53.domain.Delivery;
import com.review53.dto.DeliveryRequestDto;
import com.review53.dto.DeliveryStatusUpdateDto;
import com.review53.dto.DeliveryResponseDto;
import com.review53.dto.DeliveryStatusResponseDto;
import com.review53.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    // constructor
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) { this.deliveryRepository = deliveryRepository; }

    @Override
    public DeliveryResponseDto createDelivery(DeliveryRequestDto requestDto) {
        Delivery delivery = new Delivery(requestDto.getCustomerName());
        deliveryRepository.save(delivery);
        return new DeliveryResponseDto(delivery.getId(), delivery.getCustomerName(), delivery.getStatus(), "배송 등록이 완료되었습니다.");
    }

    @Override
    public DeliveryStatusResponseDto updateStatus(DeliveryStatusUpdateDto statusUpdateDto) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(statusUpdateDto.getDeliveryId());
        Delivery delivery = optionalDelivery.orElseThrow(() -> new IllegalArgumentException("조회되는 배송 정보가 없습니다."));
        delivery.updateStatus(statusUpdateDto.getStatus());
        return new DeliveryStatusResponseDto(delivery.getId(), delivery.getStatus(), "배송 상태 변경이 완료 되었습니다.");
    }

    // flush 이후 예외 발생 시 롤백 여부 확인
    @Override
    public void flushAndFail(DeliveryRequestDto requestDto) {
        Delivery delivery = new Delivery(requestDto.getCustomerName());
        delivery.startDelivery();

        deliveryRepository.save(delivery);
        deliveryRepository.flush();

        throw new RuntimeException("flush 이후 강제 예외 발생!");
    }

    @Override
    public void bulkProcessWithRollback(List<DeliveryRequestDto> requestDtoList) {
        for (DeliveryRequestDto requestDto : requestDtoList) {
            Delivery delivery = new Delivery(requestDto.getCustomerName());
            deliveryRepository.save(delivery);
        }
        throw new RuntimeException("다량 배송 건 저장 후 강제 예외!");
    }
}