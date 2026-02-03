package com.example.spring_prac.service;

import com.example.spring_prac.domain.Product;
import com.example.spring_prac.dto.ProductAddRequestDTO;
import com.example.spring_prac.dto.ProductResponseDTO;
import com.example.spring_prac.dto.ProductUpdateRequestDTO;
import com.example.spring_prac.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void newProduct(ProductAddRequestDTO product) {
        Product newProduct=Product.builder().name(product.getName()).stock(product.getStock()).build();
        productRepository.save(newProduct);
    }

    public List<ProductResponseDTO> list() {
        List<ProductResponseDTO> list=new ArrayList<>();
        for(Product product: productRepository.findAll()){
            list.add(ProductResponseDTO.builder().id(product.getId()).name(product.getName()).stock(product.getStock()).build());
        }
        return list;
    }

    public ProductResponseDTO getProduct(Long id) {
        Product product=findById(id);
        return ProductResponseDTO.builder().id(product.getId()).name(product.getName()).stock(product.getStock()).build();
    }

    public void updateProduct(ProductUpdateRequestDTO newProduct, Long id) {
        Product product=findById(id);
        product.setName(newProduct.getName());
        product.setStock(newProduct.getStock());
        productRepository.save(product);
    }

    public void hideProduct(Long id) {
        Product product=findById(id);
        product.setStatus(Product.Status.unavailable);
    }

    public void deleteProduct(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }


    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("해당 제품을 찾을 수 없습니다."));
    }
}
