package com.example.spring_prac.repository;

import com.example.spring_prac.domain.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDTO, Long> {


}
