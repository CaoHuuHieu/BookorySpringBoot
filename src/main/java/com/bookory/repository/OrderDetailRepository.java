package com.bookory.repository;

import com.bookory.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{


}
