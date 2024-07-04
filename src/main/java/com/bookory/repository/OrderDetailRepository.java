package com.bookory.repository;

import java.util.List;

import com.bookory.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

	List<OrderDetail> findByOrderEntityId(long id);

}
