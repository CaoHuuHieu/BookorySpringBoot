package com.bookory.services;

import com.bookory.dto.address.AddressSaveDto;
import com.bookory.dto.address.AddressUpdateDto;
import com.bookory.dto.response.AddressResponseDTO;

public interface IAddressService {
    
     AddressResponseDTO getAddressDetail(Long id);

     Long createNewAddress(AddressSaveDto addressRequest);

     Long updateAddress(Long id, AddressUpdateDto addressRequest);

     Long addAddressForUser(Long userId, AddressSaveDto address);
}
