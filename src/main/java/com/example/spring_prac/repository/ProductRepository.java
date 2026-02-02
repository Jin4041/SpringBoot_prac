package com.example.spring_prac.repository;

import com.example.spring_prac.domain.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDTO, Long> {

}
