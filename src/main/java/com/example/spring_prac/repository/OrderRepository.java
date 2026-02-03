package com.example.spring_prac.repository;

import com.example.spring_prac.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
