package com.bookory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.Tag;


public interface TagRepository extends JpaRepository<Tag, Long>{

}
