package com.bookory.repository;

import java.util.List;

import com.bookory.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long>{

	List<Promotion> findByStoreEntityId(long storeId);

	List<Promotion> findByStoreEntityIdAndStatus(long storeId, int status);
}
