package com.bookory.repository;

import java.sql.Date;
import java.util.List;

import com.bookory.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByStoreEntityId(long storeId);

	List<Order> findByStoreEntityIdAndStatus(long storeId, int status);

	List<Order> findByUserEntityId(long userId);
	
	@Query("SELECT SUM(o.totalMoney) FROM OrderEntity o")
	Long sumTotalMoney();

	@Query("SELECT SUM(o.totalMoney) FROM OrderEntity o WHERE o.createDate BETWEEN :startDate AND :endDate")
	Long sumTotalMoneyBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query("SELECT COUNT(o.id) FROM OrderEntity o WHERE o.createDate BETWEEN :startDate AND :endDate")
	Long countByCreateDateBetween(Date startDate, Date endDate);

}
