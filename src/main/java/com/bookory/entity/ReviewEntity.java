package com.bookory.entity;
import java.sql.Date;

import jakarta.persistence.*;
@Entity
@Table(name="review")

public class ReviewEntity {
	public ReviewEntity(UserEntity userEntity, BookEntity bookEntity, int star, String comment, Date createDate) {
		this.userEntity = userEntity;
		this.bookEntity = bookEntity;
		this.star = star;
		this.comment = comment;
		this.createDate = createDate;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="user_id")
	private UserEntity userEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="book_id")
	private BookEntity bookEntity;
	@Column
	private Integer star;
	@Column
	private String comment;
	@Column(name="create_date")
	private Date createDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public BookEntity getBookEntity() {
		return bookEntity;
	}
	public void setBookEntity(BookEntity bookEntity) {
		this.bookEntity = bookEntity;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public ReviewEntity() {
		super();
	}
	
	
	
}
