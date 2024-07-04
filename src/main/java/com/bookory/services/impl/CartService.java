package com.bookory.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bookory.exception.EntityNotFoundException;
import com.bookory.mapper.CartMapper;
import com.bookory.mapper.StoreMapper;
import com.bookory.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookory.EntityAndDto.CartConvert;
import com.bookory.EntityAndDto.StoreConvert;
import com.bookory.dto.request.CartRequestDTO;
import com.bookory.dto.response.CartDetailDTO;
import com.bookory.dto.response.CartResponseDTO;
import com.bookory.dto.response.StoreBasicInforDTO;
import com.bookory.entity.Book;
import com.bookory.entity.Cart;
import com.bookory.entity.Store;
import com.bookory.entity.User;
import com.bookory.repository.BookRepository;
import com.bookory.repository.CartRepository;
import com.bookory.repository.UserRepository;
@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;
	private final BookRepository bookRepository;
	private final CartMapper cartMapper;
	private final StoreMapper storeMapper;
	private final UserRepository userRepository;


	public List<CartResponseDTO> getCartsByUserId(Long userid) throws IOException{
		return null;
	}

	public Cart getCartDetailByUserIdAndBookId(long userid, long bookid){
		return cartRepository.findByUserEntityIdAndBookEntityId(userid, bookid);
	}

	public Long addNewCart(CartRequestDTO cartDto) throws Exception {
		User currentUser = AuthenticationUtils.getCurrentUser();
		Cart cart = cartRepository.findByUserEntityIdAndBookEntityId(currentUser.getId(), cartDto.getBookId());
		if (cart != null) {
			int amount = cart.getAmount() + cartDto.getAmount();
			cart.setAmount(amount);
		} else {
			Book book = bookRepository.findById(cartDto.getBookId()).orElseThrow(() -> new EntityNotFoundException("error.entity_not_found", "Book", cartDto.getBookId()));
			cart = new Cart();
			cart.setUser(currentUser);
			cart.setBook(book);
			cart.setAmount(cartDto.getAmount());
		}
		cartRepository.save(cart);
		return cart.getId();
	}

	public Long deleteCartItem(long id){
		cartRepository.deleteById(id);
		return id;
	}

}
