package com.bookory.repository;

import java.util.List;

import com.bookory.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
