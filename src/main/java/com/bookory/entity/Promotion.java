package com.bookory.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="promotion", schema = "app")
public class Promotion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private Integer discount;

	@Column(name="start_date")
	private Date startDate;

	@Column(name="end_date")
	private Date endDate;

	@Column(name="create_date")
	private Date createDate;

	@Column(name="update_date")
	private Date updateDate;

	@ManyToOne
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private Store store;

	@Column
	private Integer status;
	
}
