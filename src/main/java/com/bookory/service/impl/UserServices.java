package com.bookory.service.impl;

import java.io.IOException;

import com.bookory.dto.user.*;
import com.bookory.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServices {

	private final UserRepository userRepository;
	private final AddressService addressServices;
	private final UserMapper userMapper;

	public Page<UserListDto> getUserList(Pageable pageable, UserFilterDto filter) throws IOException{
		return null;
	}

	public UserDetailDto getUserDetail(Long userId) throws IOException{
		return null;
	}
	
	public Long createNewUser(UserSaveDto userDto, MultipartFile file){
		return null;
	}
	
	public Long login(UserLoginDto userDto){
		return null;
	}

	public Long register(UserRegisterDto userDto){
		return null;
	}
	
	public Long updateUser(Long id, UserUpdateDto userDto, MultipartFile file) throws IOException{
		return null;
	}	

	public Long activeUser(Long userId) {
		return null;
	}
}
