package com.example.spring_prac.service;

import com.example.spring_prac.domain.Product;
import com.example.spring_prac.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void addProduct(Product product) {
         productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("해당 제품을 찾을 수 없습니다."));
    }

    public void updateProduct(Product newProduct, Long id) {
        Product product=findById(id);
        product.setName(newProduct.getName());
        product.setStock(newProduct.getStock());
        productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
