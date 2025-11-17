package com.review52.service;

import com.review52.repository.MemberRepository;
import com.review52.repository.ProductRepository;
import com.review52.repository.OrderRepository;
import com.review52.repository.OrderItemRepository;
import com.review52.dto.OrderItemDto;
import com.review52.dto.OrderRequestDto;
import com.review52.dto.OrderResponseDto;
import com.review52.domain.Member;
import com.review52.domain.Product;
import com.review52.domain.Order;
import com.review52.domain.OrderItem;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    // constructor
    public OrderServiceImpl(MemberRepository memberRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Optional<Member> optionalMember = memberRepository.findById(requestDto.getMemberId());
        Member member = optionalMember.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. [ID = " + requestDto.getMemberId() + "]"));

        Order order = new Order("주문 완료", member);

        for (OrderItemDto itemDto : requestDto.getItems()) {
            Optional<Product> optionalProduct = productRepository.findById(itemDto.getProductId());
            Product product = optionalProduct.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다. [productId = " + itemDto.getProductId() + "]"));
            OrderItem orderItem = new OrderItem(itemDto.getQuantity(), itemDto.getOrderPrice(), product);
            order.addOrderItem(orderItem);
        }
        orderRepository.save(order);

        List<OrderItemDto> itemDtos = order.getOrderItems().stream().map(
                orderItem -> new OrderItemDto(orderItem.getProduct().getId(), orderItem.getQuantity(), orderItem.getOrderPrice())
        ).collect(Collectors.toList());

        return new OrderResponseDto(order.getId(), order.getMember().getName(), order.getOrderDate(), order.getStatus(), itemDtos);
    }

    @Override
    @Transactional(readOnly=true)
    public List<OrderResponseDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> responseDtos = new ArrayList<>();

        for (Order order : orders) {
            List<OrderItemDto> itemDtos = order.getOrderItems().stream().map(
                    item -> new OrderItemDto(item.getProduct().getId(), item.getQuantity(), item.getOrderPrice())
            ).collect(Collectors.toList());

            OrderResponseDto responseDto = new OrderResponseDto(order.getId(), order.getMember().getName(), order.getOrderDate(), order.getStatus(), itemDtos);
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }
}