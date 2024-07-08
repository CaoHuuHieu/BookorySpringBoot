package com.bookory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
