package com.bookory.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.Store;



public interface StoreRepository extends JpaRepository<Store, Long>{
}
