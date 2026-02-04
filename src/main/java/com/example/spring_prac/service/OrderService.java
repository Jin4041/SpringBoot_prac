package com.example.spring_prac.service;

import com.example.spring_prac.domain.Order;
import com.example.spring_prac.domain.type.Status;
import com.example.spring_prac.dto.OrderResponseDTO;
import com.example.spring_prac.domain.Product;
import com.example.spring_prac.dto.ProductUpdateRequestDTO;
import com.example.spring_prac.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional
    synchronized public ResponseEntity<String> newOrder(Long productId) {
        Product product=productService.findById(productId);
        if(product.getStock()==0) {
            throw new IllegalStateException("재고 소진");
        } else if (product.getStatus()== Status.UNAVAILABLE) {
            throw new IllegalStateException("판매 중지");
        }else{
            if(product.getStock()==1)
                product.setStatus(Status.UNAVAILABLE);
            product.setStock(product.getStock()-1);
            ProductUpdateRequestDTO newProduct=ProductUpdateRequestDTO.builder().stock(product.getStock()).build();
            productService.updateProduct(newProduct, productId);
            return ResponseEntity.ok("주문 완료");
        }
    }

    public OrderResponseDTO findById(Long id) {
        Order order= orderRepository.findById(id).orElseThrow(()->new RuntimeException("해당 주문을 찾을 수 없습니다."));
        return OrderResponseDTO.builder().id(order.getId()).productName(order.getProduct().getName()).build();
    }

    public List<OrderResponseDTO> findAll() {
        List<OrderResponseDTO> list =new ArrayList<>();
        for(Order order : orderRepository.findAll()){
            list.add(OrderResponseDTO.builder().id(order.getId()).productName(order.getProduct().getName()).build());
        }
        return list;
    }
}
