package com.bookory.service.impl;

import com.bookory.dto.address.AddressSaveDto;
import com.bookory.dto.address.AddressUpdateDto;
import com.bookory.exception.EntityNotFoundException;
import com.bookory.mapper.AddressMapper;
import com.bookory.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.bookory.dto.response.AddressResponseDTO;
import com.bookory.entity.Address;
import com.bookory.entity.User;
import com.bookory.repository.AddressRepository;
import com.bookory.repository.UserRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

	AddressRepository addressRepository;

	UserRepository userRepository;

	AddressMapper addressMapper;

	public AddressService(AddressRepository addressRepository, UserRepository userRepository, AddressMapper addressMapper){
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		this.addressMapper = addressMapper;
	}


	private Address getAddressById(Long id){
		return addressRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("error.entity_not_found", "Address", id));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AddressResponseDTO getAddressDetail(Long id) {
		Address address = getAddressById(id);
		return addressMapper.addressToAddressDto(address);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long createNewAddress(AddressSaveDto addressDto) {
		Address addressEntity = addressMapper.addressSaveDtoToAddress(addressDto);
		addressRepository.save(addressEntity);
		return addressEntity.getId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long updateAddress(Long id, AddressUpdateDto addressDto) {
		return id;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long addAddressForUser(Long userId, AddressSaveDto addressDto) {
		User userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("error.entity_not_found", "User", userId));
		Address address = addressMapper.addressSaveDtoToAddress(addressDto);
		address = addressRepository.save(address);
		userEntity.setAddress(address);
		userRepository.save(userEntity);
		return userId;
	}
	
}
