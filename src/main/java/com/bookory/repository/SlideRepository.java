package com.bookory.repository;

import java.util.List;

import com.bookory.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlideRepository extends JpaRepository<Slide, Long>{

	List<Slide> findByStatus(int i);

}
