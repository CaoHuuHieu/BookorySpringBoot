package com.bookory.service.impl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bookory.dto.order.OrderDetailDto;
import com.bookory.dto.order.OrderListDto;
import com.bookory.dto.order.OrderSaveDto;
import com.bookory.mapper.OrderDetailMapper;
import com.bookory.mapper.OrderMapper;
import com.bookory.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bookory.dto.response.OrderDetailResponseDTO;
import com.bookory.dto.response.OrderResponseDTO;
import com.bookory.dto.response.OrderResponseForStore;
import com.bookory.entity.Book;
import com.bookory.entity.Cart;
import com.bookory.entity.User;
import com.bookory.object.EmailProvider;
import com.bookory.repository.BookRepository;
import com.bookory.repository.CartRepository;
import com.bookory.repository.OrderDetailRepository;
import com.bookory.repository.OrderRepository;
import com.bookory.repository.StoreRepository;
import com.bookory.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

	private final OrderRepository orderRepository;
	private final StoreRepository storeRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final BookRepository bookRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final OrderMapper orderConvert;
	private final OrderDetailMapper orderDetailConvert;
	private final EmailProvider emailProvider ;


	public Page<OrderListDto> getOrderOfStore(Long storeId) {
		return null;
	}

	public OrderDetailDto getOrderDetail(Long orderId){
		return null;
	}
	
	public void createNewOrder(OrderSaveDto orderDto) {

	}

	public List<OrderListDto> getOrderOfUser(Long userId) throws IOException {
		return null;
	}

	public Long updateOrderStatus(Long orderId, Integer state) {
		return null;
	}
	
	public void sendMail(long userId, long orderId, int status) {
		User userEntity = userRepository.findById(userId).orElse(null);
		if(userEntity != null) {
			String mailTo = userEntity.getEmail();	
			String subject = "BOOKORY-Thông báo đơn hàng";
			String body = "";
			if(status == 0) {
				body = "Đơn hàng #"+orderId+" của bạn đã đặt thành công. Vui lòng xem mục quản lý lịch sử đơn hàng để theo dõi trạng thái đơn hàng.";
			}
			if(status == 1) {
				body = "Đơn hàng #"+orderId+" của bạn đã được xác nhận. Vui lòng xem mục quản lý lịch sử đơn hàng để theo dõi trạng thái đơn hàng.";
			}
			if(status == 2) {
				body = "Đơn hàng #"+orderId+" của bạn đã được giao cho đơn vị vận chuyển. Vui lòng xem mục quản lý lịch sử đơn hàng để theo dõi trạng thái đơn hàng.";
			}
			if(status == 3) {
				body = "Đơn hàng #"+orderId+" của bạn đã giao. Vui lòng xác nhận đã giao.";
			} 
			if(status == 4) {
				body = "Đơn hàng #"+orderId+" của bạn đã đưuọc hủy. Vui lòng xem mục quản lý lịch sử đơn hàng để theo dõi trạng thái đơn hàng.";
			} 
			emailProvider.sendEmail(mailTo, subject, body);
		}
	}

}
