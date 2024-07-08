package com.bookory.repository;

import com.bookory.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
