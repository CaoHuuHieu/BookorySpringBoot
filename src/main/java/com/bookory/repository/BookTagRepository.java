package com.bookory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookory.entity.BookTag;
import com.bookory.entity.Tag;

public interface BookTagRepository extends JpaRepository<BookTag, Long>{

	@Query("SELECT tag from BookTagEntity bt where bt.book.id =:bookId")
	List<Tag> findByBookId(@Param("bookId") long id);
}
