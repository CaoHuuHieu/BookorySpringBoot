package com.bookory.service;

import com.bookory.dto.contact.ContactDetailDto;
import com.bookory.dto.contact.ContactFilterDto;
import com.bookory.dto.contact.ContactListDto;
import com.bookory.dto.contact.ContactSaveDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface IContactService {
    Page<ContactListDto> getAllContact(Pageable pageable, ContactFilterDto filter);

    ContactDetailDto getContactDetail(Long id);

    Long createNewContact(ContactSaveDto contactDto);

    void deleteContact(Long id);
}
