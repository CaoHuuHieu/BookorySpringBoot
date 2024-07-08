package com.bookory.entity;

import java.sql.Date;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Order{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User userEntity;

	@OneToOne
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private Store storeEntity;

	@Column
	private String name;

	@Column
	private String phone;

	@Column
	private String address;

	@Column(name="create_date")
	private Date createDate;

	@Column
	private String note;

	@Column
	private Integer status;

	@Column
	private Integer payment;

	@Column(name="total_money")
	private Long totalMoney;

	@Column(name="transport_fee")
	private Long transportFee;

}
