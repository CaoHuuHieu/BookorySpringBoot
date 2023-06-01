package com.bookory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="address")

public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="province_id")
	private int provinceId;
	@Column(name="district_id")
	private int districtId;
	@Column(name="ward_id")
	private int wardId;
	@Column(name="full_address")
	private String fullAddress;
	public AddressEntity(int provinceId, int districtId, int wardId, String fullAddress) {
		this.provinceId = provinceId;
		this.districtId = districtId;
		this.wardId = wardId;
		this.fullAddress = fullAddress;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public int getWardId() {
		return wardId;
	}
	public void setWardId(int wardId) {
		this.wardId = wardId;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	
	
	public AddressEntity() {
		super();
	}
	public AddressEntity(long id, int provinceId, int districtId, int wardId, String fullAddress) {
		super();
		this.id = id;
		this.provinceId = provinceId;
		this.districtId = districtId;
		this.wardId = wardId;
		this.fullAddress = fullAddress;
	}
	
	
	
	
	
}
