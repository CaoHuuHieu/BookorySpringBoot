package com.bookory.entity;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="review", schema = "app")
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="user_id")
	private User userEntity;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="book_id")
	private Book bookEntity;

	@Column
	private Integer star;

	@Column
	private String comment;

	@Column(name="create_date")
	private Date createDate;

}
