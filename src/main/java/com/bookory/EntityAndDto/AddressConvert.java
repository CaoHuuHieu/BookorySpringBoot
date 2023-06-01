package com.bookory.EntityAndDto;

import org.springframework.stereotype.Component;

import com.bookory.dto.request.AddressRequestDTO;
import com.bookory.dto.response.AddressResponseDTO;
import com.bookory.entity.AddressEntity;

@Component
public class AddressConvert {
	public AddressResponseDTO toAddressResponseDTO(AddressEntity addressEntity) {
		AddressResponseDTO address = new AddressResponseDTO();
		address.setId(addressEntity.getId());
		address.setProvinceId(addressEntity.getProvinceId());
		address.setDistrictId(addressEntity.getDistrictId());
		address.setWardId(addressEntity.getWardId());
		address.setFullAddress(addressEntity.getFullAddress());
		return address;
	}
	public AddressEntity toAddressEntity(AddressRequestDTO address) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setProvinceId(address.getProvinceId());
		addressEntity.setDistrictId(address.getDistrictId());
		addressEntity.setWardId(address.getWardId());
		addressEntity.setFullAddress(address.getFullAddress());
		return addressEntity;
	}
}
