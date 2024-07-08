package com.bookory.service.impl;

import java.io.IOException;
import java.util.List;

import com.bookory.dto.cart.CartSaveDto;
import com.bookory.exception.EntityNotFoundException;
import com.bookory.mapper.CartMapper;
import com.bookory.mapper.StoreMapper;
import com.bookory.service.ICartService;
import com.bookory.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.bookory.dto.response.CartResponseDTO;
import com.bookory.entity.Book;
import com.bookory.entity.Cart;
import com.bookory.entity.User;
import com.bookory.repository.BookRepository;
import com.bookory.repository.CartRepository;
import com.bookory.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

	private  CartRepository cartRepository;
	private  BookRepository bookRepository;
	private  CartMapper cartMapper;
	private  StoreMapper storeMapper;
	private  UserRepository userRepository;


	public List<CartResponseDTO> getCartsByUserId(Long userid) throws IOException{
		return null;
	}

	public Cart getCartDetailByUserIdAndBookId(long userid, long bookid){
		return null;
	}

	public Long createNewCart(CartSaveDto cartDto) throws Exception {
		return null;
	}

	public Long deleteCartItem(long id){
		cartRepository.deleteById(id);
		return id;
	}

}
