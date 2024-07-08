package com.bookory.service.impl;


import com.bookory.dto.contact.ContactDetailDto;
import com.bookory.dto.contact.ContactFilterDto;
import com.bookory.dto.contact.ContactListDto;
import com.bookory.dto.contact.ContactSaveDto;
import com.bookory.entity.Contact;
import com.bookory.exception.EntityNotFoundException;
import com.bookory.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookory.repository.ContactRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactService implements IContactService {

	ContactRepository contactRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<ContactListDto> getAllContact(Pageable pageable, ContactFilterDto filter){
		return null;
	}

	private Contact getContactById(Long id){
		return contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("error.entity_not_found", "Contact", id));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ContactDetailDto getContactDetail(Long id){
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long createNewContact(ContactSaveDto contactDto){
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteContact(Long id){
		 contactRepository.deleteById(id);
	}
}
