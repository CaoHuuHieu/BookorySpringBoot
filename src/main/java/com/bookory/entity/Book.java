package com.bookory.entity;


import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="book", schema = "app")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String name;

	private String image;

	private String author;

	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName = "id")
	private Category category;

	private String publishing;

	@ManyToOne
	@JoinColumn(name="promotion_id", referencedColumnName = "id")
	private Promotion promotion;

	@Column(name="publishing_year")
	private int publishingYear;

	@ManyToOne
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private Store store;

	@Column(name="page_number")
	private int pageNumber;

	private int length;

	private int width;

	private int height;

	private int weight;

	private int quantity;

	private int price;

	@Column(name="quantity_sold")
	private int quantitySold;

	@Column(name="create_date")
	private Date createDate;

	@Column(name="update_date")
	private Date updateDate;

	private int status;

	private String description;
}

