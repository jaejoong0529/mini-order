package com.sparta.miniorder.order.service;

import com.sparta.miniorder.order.dto.OrderRequest;
import com.sparta.miniorder.order.dto.OrderResponse;
import com.sparta.miniorder.order.entity.Order;
import com.sparta.miniorder.order.repository.OrderRepository;
import com.sparta.miniorder.product.entity.Product;
import com.sparta.miniorder.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderResponse create(OrderRequest request) {
        Product product = productService.findProductById(request.getProductId());
        Order order = new Order(product);
        Order saved = orderRepository.save(order);
        return new OrderResponse(saved);

    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long orderId) {
        Order order = findOrderById(orderId);
        return new OrderResponse(order);
    }

    private Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다. id=" + id));
    }
}
