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
        Member member = optionalMember.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto item : requestDto.getItems()) {
            Optional<Product> optionalProduct = productRepository.findById(item.getProductId());
            Product product = optionalProduct.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
            OrderItem orderItem =  new OrderItem(item.getQuantity(), item.getOrderPrice(), product);
            items.add(orderItem);
        }

        Order order = new Order();
        for (OrderItem item : items) {
            order.addOrderItem(item);
        }
        orderRepository.save(order);

        return new OrderResponseDto(order.getId(), member.getName(), order.getOrderDate(), order.getStatus(), requestDto.getItems());
    }

    @Override
    @Transactional(readOnly=true)
    public List<OrderResponseDto> getOrders() {
        // 결과 리스트
        List<OrderResponseDto> result = new ArrayList<>();
        // 전체 주문 조회
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            // 주문 별 상품들 조회
            List<OrderItemDto> itemDtos = new ArrayList<>();
            List<OrderItem> items = new ArrayList<>();
            for (OrderItem orderItem : order.getOrderItems()) {
                OrderItemDto itemDto = new OrderItemDto(orderItem.getProduct().getId(), orderItem.getQuantity(), orderItem.getOrderPrice());
                itemDtos.add(itemDto);
            }
            OrderResponseDto responseDto = new OrderResponseDto(order.getId(), order.getMember().getName(), order.getOrderDate(), order.getStatus(), itemDtos);
            result.add(responseDto);
        }
        return result;
    }
}