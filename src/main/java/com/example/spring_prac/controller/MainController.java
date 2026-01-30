package com.example.spring_prac.controller;

import com.example.spring_prac.domain.OrderInfomation;
import com.example.spring_prac.domain.Product;
import com.example.spring_prac.service.OrderService;
import com.example.spring_prac.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    private final OrderService orderService;

    //필수 과제
    //1. 상품 CRUD
    @PostMapping(value = "/addProduct")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping(value = "/getProduct")
    public List<Product> getProduct() {
        return productService.findAll();
    }

    @GetMapping(value = "/getProduct/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping(value = "/updateProduct/{id}")
    public void updateProduct(@RequestBody Product product,@PathVariable Long id) {
        productService.updateProduct(product,id);
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    //2. 주문 생성 및 조회
    @PostMapping("/newOrder")
    public void addOrder(@RequestBody Long productId) {
        orderService.newOrder(productId);
    }

    @GetMapping("/orderList")
    public List<OrderInfomation> getOrderList() {
        return orderService.findAll();
    }

    @GetMapping("/order/{id}")
    public OrderInfomation getOrder(@PathVariable Long id) {
        return orderService.findById(id);
    }

    //도전 과제
    //1. 주문 목록 조회(N+1 문제 예방)
    //2. 상품 재고 차감(재고가 원자적으로 차감되도록 고려)

}
