package com.bookory.repository;

import java.util.List;

import com.bookory.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Long>{
}
