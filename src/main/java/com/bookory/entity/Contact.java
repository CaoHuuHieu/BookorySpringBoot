package com.bookory.entity;

import java.sql.Date;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="contact")
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String gmail;

	@Column
	private String subject;

	@Column
	private String content;

	@Column(name="create_date")
	private Date createDate;

	@Column
	private Integer status;

}
