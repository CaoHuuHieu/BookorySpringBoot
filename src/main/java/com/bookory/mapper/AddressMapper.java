package com.bookory.mapper;

import com.bookory.dto.address.AddressSaveDto;
import com.bookory.dto.address.AddressUpdateDto;
import com.bookory.dto.response.AddressResponseDTO;
import com.bookory.entity.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    AddressResponseDTO addressToAddressDto(Address address);

    Address addressSaveDtoToAddress(AddressSaveDto addressDto);

    Address addressUpdateDtoToAddress(AddressUpdateDto addressDto, Address addressEntity);
}
