package com.example.spring_prac.repository;

import com.example.spring_prac.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
