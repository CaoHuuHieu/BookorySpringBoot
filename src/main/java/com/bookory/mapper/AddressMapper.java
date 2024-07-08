package com.bookory.mapper;

import com.bookory.dto.address.AddressSaveDto;
import com.bookory.dto.response.AddressResponseDTO;
import com.bookory.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressResponseDTO addressToAddressDto(Address address);

    Address addressSaveDtoToAddress(AddressSaveDto addressDto);

}
