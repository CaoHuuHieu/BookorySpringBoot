package com.bookory.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookory.entity.Store;



public interface StoreRepository extends JpaRepository<Store, Long>{
	@Query("SELECT COUNT(s) FROM StoreEntity s WHERE s.createDate BETWEEN :startDate AND :endDate")
	Long countByCreateDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
