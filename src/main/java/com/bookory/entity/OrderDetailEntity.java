package com.bookory.entity;
import jakarta.persistence.*;
@Entity
@Table(name="orderdetail")
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="order_id")
	private OrderEntity orderEntity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="book_id")
	private BookEntity bookEntity;
	@Column
	private Integer amount;
	@Column
	private Integer price;
	@Column
	private Integer discount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OrderEntity getOrderEntity() {
		return orderEntity;
	}
	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}
	public BookEntity getBookEntity() {
		return bookEntity;
	}
	public void setBookEntity(BookEntity bookEntity) {
		this.bookEntity = bookEntity;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "OrderDetailEntity [id=" + id + ", orderEntity=" + orderEntity + ", bookEntity=" + bookEntity
				+ ", amount=" + amount + ", price=" + price + ", discount=" + discount + "]";
	}
	
	
	
}
