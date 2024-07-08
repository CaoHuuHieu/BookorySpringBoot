package com.bookory.entity;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book_tag")
public class BookTag {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tag_id", referencedColumnName = "id")
	private Tag tag;

}
