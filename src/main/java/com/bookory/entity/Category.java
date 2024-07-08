package com.bookory.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="category")
public class Category{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String thumbnail;

	@Column
	private Integer status;
}
