package com.bookory.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookory.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);

	@Query("SELECT COUNT(u) FROM UserEntity u WHERE u.role = 1")
	Long countByRoleId();

	@Query("SELECT COUNT(u) FROM UserEntity u WHERE u.createDate BETWEEN :startDate AND :endDate")
	Long countByCreateDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	Boolean existsByEmail(String email);

}
