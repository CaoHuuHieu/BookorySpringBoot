package com.bookory.entity;

import java.sql.Date;

import jakarta.persistence.*;
@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(name="full_name")
	private String fullName;
	@Column
	private String email;
	@Column
	private String phone;
	@Column
	private String password;
	@Column
	private String gender;
	@OneToOne
	@JoinColumn(name="address_id", referencedColumnName = "id")
	private AddressEntity addressEntity;
	@Column(name="create_date")
	private Date createDate;
	@Column(name="update_date")
	private Date updateDate;
	@Column
	private String avatar;
	@Column
	private Integer role;
	@Column
	private Integer status;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private StoreEntity storeEntity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public AddressEntity getAddressEntity() {
		return addressEntity;
	}
	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public StoreEntity getStoreEntity() {
		return storeEntity;
	}
	public void setStoreEntity(StoreEntity storeEntity) {
		this.storeEntity = storeEntity;
	}
	
	
	
	
}
