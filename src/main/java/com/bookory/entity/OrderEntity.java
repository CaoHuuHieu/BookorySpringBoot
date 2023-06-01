package com.bookory.entity;

import java.sql.Date;


import jakarta.persistence.*;
@Entity
@Table(name="orders")
public class OrderEntity {
	@Id
	private long id;
	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private UserEntity userEntity;
	@OneToOne
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private StoreEntity storeEntity;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public StoreEntity getStoreEntity() {
		return storeEntity;
	}
	public void setStoreEntity(StoreEntity storeEntity) {
		this.storeEntity = storeEntity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public Long getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Long getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(Long transportFee) {
		this.transportFee = transportFee;
	}

	
	
	
	
	
}
