package com.bookory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
