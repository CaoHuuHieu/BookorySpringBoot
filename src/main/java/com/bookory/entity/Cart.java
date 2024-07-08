package com.bookory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="cart")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name="book_id", referencedColumnName = "id")
	private Book book;

	@Column
	private int amount;
	
}
