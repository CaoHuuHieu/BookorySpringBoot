package com.bookory.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookory.EntityAndDto.CartConvert;
import com.bookory.EntityAndDto.StoreConvert;
import com.bookory.dto.request.CartRequestDTO;
import com.bookory.dto.response.CartDetailDTO;
import com.bookory.dto.response.CartResponseDTO;
import com.bookory.dto.response.StoreBasicInforDTO;
import com.bookory.entity.BookEntity;
import com.bookory.entity.CartEntity;
import com.bookory.entity.StoreEntity;
import com.bookory.entity.UserEntity;
import com.bookory.repositories.BookRepository;
import com.bookory.repositories.CartRepository;
import com.bookory.repositories.UserRepository;
@Service
public class CartServices {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	CartConvert cartConvert;
	@Autowired
	StoreConvert storeConvert;
	@Autowired
	UserRepository userRepository;
	public List<CartResponseDTO> getAllCartDetailByUserId(long userid) throws IOException{
		//Tìm kiếm tất cả các cửa hàng có trong giỏ hàng
		List<StoreEntity> storeIncart = cartRepository.getAllStoreInCart(userid);
		if(storeIncart.size() > 0) {
			//Sắp xếp các sản phẩm theo cửa hàng
			List<CartResponseDTO> cartResponseDTOs  = new ArrayList<CartResponseDTO>();
			StoreBasicInforDTO storeBasicInfor;
			List<CartEntity> cartEntities;
			for(StoreEntity storeEntity:storeIncart) {
				storeBasicInfor = storeConvert.toStoreBasicInforDTO(storeEntity);
				cartEntities = cartRepository.getAllCartDetailByStoreIdAndUserId(storeEntity.getId(), userid);
				List<CartDetailDTO>  cartDetailDTOs = cartConvert.toCartDetailDTO(cartEntities);
				cartResponseDTOs.add(new CartResponseDTO(storeBasicInfor, cartDetailDTOs));
			}
			return cartResponseDTOs;
		}else
			return null;
	}
	
	public CartEntity getCartDetailByID(long id){
		return cartRepository.findById(id).orElse(null);
	}
	
	public CartEntity getCartDetailByUserIdAndBookId(long userid, long bookid){
		return cartRepository.findByUserEntityIdAndBookEntityId(userid, bookid);
	}

	
	public CartEntity addNewCart(CartRequestDTO cart){
		CartEntity cartFind = getCartDetailByUserIdAndBookId(cart.getUserId(), cart.getBookId());
		UserEntity userEntity = userRepository.findById(cart.getUserId()).orElse(null);
		BookEntity bookEntity = bookRepository.findById(cart.getBookId()).orElse(null);
		if(bookEntity != null && userEntity != null && ((bookEntity.getStoreEntity() == null)||((bookEntity.getStoreEntity() != null && bookEntity.getStoreEntity().getId() != userEntity.getStoreEntity().getId())) )) {
			if(cartFind != null) {
				int amount = cartFind.getAmount() + cart.getAmount();
				return updateAmount(cartFind.getId(), amount > 10 ? 10 : amount);
			}else {
				CartEntity cartEntity = cartConvert.toCartEntity(cart);
				return  cartRepository.save(cartEntity);
			}
		}else
			return null;
	}
	public CartEntity updateAmount(long id, int quantity){
		CartEntity cartEntity = getCartDetailByID(id);
		cartEntity.setAmount(quantity);
		return cartRepository.save(cartEntity);
	}
	
	public CartEntity deleteCartDetail(long id){
		cartRepository.deleteById(id);
		return cartRepository.findById(id).orElse(null);
	}

	public List<StoreEntity> getAllStoreInCart(long userid) {
		return cartRepository.getAllStoreInCart(userid);
	}

	public CartEntity selectToPay(long id) {
		CartEntity cartEntity = getCartDetailByID(id);
		if(cartEntity.getStatus() == 1)
			cartEntity.setStatus(0);
		else
			cartEntity.setStatus(1);
		return cartRepository.save(cartEntity);
	}
	public List<CartEntity> getCartDetailSelected(long userId) {
		List<CartEntity> cartEntities = cartRepository.findByUserEntityIdAndStatus(userId, 1);
		return cartEntities;
	}

	public List<CartResponseDTO> getCartSelectedToPay(long userId) throws IOException {
		List<StoreEntity> storeInCartSelected = cartRepository.getAllStoreInCartSelected(userId, 1);
		if(storeInCartSelected.size() > 0) {
			List<CartResponseDTO> cartResponseDTOs  = new ArrayList<CartResponseDTO>();
			StoreBasicInforDTO storeBasicInfor;
			List<CartEntity> cartEntities;
			for(StoreEntity storeEntity:storeInCartSelected) {
				cartEntities = cartRepository.findByStoreIdAndUserIdAndStatus(storeEntity.getId(), userId, 1);
				if(cartEntities.size() > 0) {
					storeBasicInfor = storeConvert.toStoreBasicInforDTO(storeEntity);
					List<CartDetailDTO>  cartDetailDTOs = cartConvert.toCartDetailDTO(cartEntities);
					cartResponseDTOs.add(new CartResponseDTO(storeBasicInfor, cartDetailDTOs));
				}
			}
			return cartResponseDTOs;
		}else
			return null;
	
	}
	public void setItemStatus(Long[] cartIds) {
		cartRepository.updateStatusByIdIn(cartIds);
	}

}
