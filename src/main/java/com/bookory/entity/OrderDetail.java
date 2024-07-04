package com.bookory.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="order_detail", schema = "app")
public class OrderDetail{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="book_id")
	private Book bookEntity;

	@Column
	private Integer amount;

	@Column
	private Integer price;

	@Column
	private Integer discount;
}
