package com.example.spring_prac.service;

import com.example.spring_prac.domain.ProductDTO;
import com.example.spring_prac.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void addProduct(ProductDTO product) {
         productRepository.save(product);
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll();
    }

    public ProductDTO findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("해당 제품을 찾을 수 없습니다."));
    }

    public void updateProduct(ProductDTO newProduct, Long id) {
        ProductDTO product=findById(id);
        product.setName(newProduct.getName());
        product.setStock(newProduct.getStock());
        productRepository.save(product);
    }

    public void hideProduct(Long id) {
        ProductDTO product=findById(id);
        product.setStatus(ProductDTO.Status.unavailable);
    }

    public void deleteProduct(Long id) {
        ProductDTO product = findById(id);
        productRepository.delete(product);
    }
}
