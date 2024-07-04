package com.bookory.dto.address;


import lombok.Data;

@Data
public class AddressSaveDto {

	private int provinceId;

	private int districtId;

	private int wardId;

	private String fullAddress;

}
