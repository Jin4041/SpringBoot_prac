package com.example.spring_prac.controller;

import com.example.spring_prac.domain.OrderInformation;
import com.example.spring_prac.domain.ProductDTO;
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
    public void addProduct(@RequestBody ProductDTO product) {
        productService.addProduct(product);
    }

    @GetMapping(value = "/getProducts")
    public List<ProductDTO> getProducts() {
        return productService.findAll();
    }

    @GetMapping(value = "/getProduct/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping(value = "/updateProduct/{id}")
    public void updateProduct(@RequestBody ProductDTO product, @PathVariable Long id) {
        productService.updateProduct(product,id);
    }

    @PutMapping(value = "/hideProduct{id}")
    public void hideProduct(@PathVariable Long id) {
        productService.hideProduct(id);
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    //2. 주문 생성 및 조회
    @GetMapping("/newOrder/{productId}")
    public String newOrder(@PathVariable Long productId) {
        return orderService.newOrder(productId);
    }

    @GetMapping("/orderList")
    public List<OrderInformation> orderList() {
        return orderService.findAll();
    }

    @GetMapping("/order/{id}")
    public OrderInformation findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    //도전 과제
    //1. 주문 목록 조회(N+1 문제 예방)
    //2. 상품 재고 차감(재고가 원자적으로 차감되도록 고려)

}
