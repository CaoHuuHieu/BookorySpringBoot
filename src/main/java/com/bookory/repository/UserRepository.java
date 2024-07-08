package com.bookory.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
