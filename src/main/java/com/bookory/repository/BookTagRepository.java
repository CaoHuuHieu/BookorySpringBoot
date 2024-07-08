package com.bookory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.BookTag;

public interface BookTagRepository extends JpaRepository<BookTag, Long>{
}
