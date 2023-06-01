package com.bookory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long>{
	
}
