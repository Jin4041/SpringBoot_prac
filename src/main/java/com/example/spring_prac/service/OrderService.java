package com.example.spring_prac.service;

import com.example.spring_prac.domain.Order;
import com.example.spring_prac.domain.OrderInfomation;
import com.example.spring_prac.domain.Product;
import com.example.spring_prac.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public void newOrder(Long productId) {
        Product product=productService.findById(productId);
        //todo: 제품의 잔여수량이 0일 경우 예외처리 외의 다른 처리방식
        if(product.getStock()==0) {
            throw new RuntimeException("해당 제품의 재고가 없습니다.");
        }else{
            product.setStock(product.getStock()-1);
            productService.updateProduct(product, productId);
            orderRepository.save(Order.builder().product(product).build());
        }
    }

    public OrderInfomation findById(Long id) {
        Order order= orderRepository.findById(id).orElseThrow(()->new RuntimeException("해당 주문을 찾을 수 없습니다."));
        return new OrderInfomation(order.getId(),order.getProduct().getName());
    }

    public List<OrderInfomation> findAll() {
        List<OrderInfomation> list =new ArrayList<>();
        for(Order order : orderRepository.findAll()){
            list.add(new OrderInfomation(order.getId(),order.getProduct().getName()));
        }
        return list;
    }
}
