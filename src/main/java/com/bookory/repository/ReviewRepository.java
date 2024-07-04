package com.bookory.repository;

import java.util.List;

import com.bookory.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long>{

	List<Review> findByBookEntityId(long bookId);

}
