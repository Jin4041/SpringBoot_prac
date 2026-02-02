package com.example.spring_prac.service;

import com.example.spring_prac.domain.OrderDTO;
import com.example.spring_prac.domain.OrderInformation;
import com.example.spring_prac.domain.ProductDTO;
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

    synchronized public String newOrder(Long productId) {
        ProductDTO product=productService.findById(productId);
        if(product.getStock()==0) {
            return "해당 제품의 재고가 없습니다.";
        } else if (product.getStatus()== ProductDTO.Status.unavailable) {
            return "해당 제품은 이용이 중지되었습니다.";
        }else{
            if(product.getStock()==1)
                product.setStatus(ProductDTO.Status.unavailable);
            product.setStock(product.getStock()-1);
            productService.updateProduct(product, productId);
            orderRepository.save(OrderDTO.builder().product(product).build());
            return "주문이 완료되었습니다.";
        }
    }

    public OrderInformation findById(Long id) {
        OrderDTO order= orderRepository.findById(id).orElseThrow(()->new RuntimeException("해당 주문을 찾을 수 없습니다."));
        return new OrderInformation(order.getId(),order.getProduct().getName());
    }

    public List<OrderInformation> findAll() {
        List<OrderInformation> list =new ArrayList<>();
        for(OrderDTO order : orderRepository.findAll()){
            list.add(new OrderInformation(order.getId(),order.getProduct().getName()));
        }
        return list;
    }
}
