package com.bookory.dto.book;

import lombok.Data;

@Data
public class BookListDto {

	private Long id;

	private String name;

	private String author;

	private Integer price;

	private Integer discount;

	private String image;

	private Integer status;

	private Integer quantity;

	private Integer quantitySold;

	private String storeName;

}
