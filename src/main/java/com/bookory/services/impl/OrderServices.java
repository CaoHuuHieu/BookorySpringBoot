package com.bookory.services.impl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookory.EntityAndDto.OrderConvert;
import com.bookory.EntityAndDto.OrderDetailConvert;
import com.bookory.dto.request.OrderRecipient;
import com.bookory.dto.request.OrderRequestDTO;
import com.bookory.dto.request.OrderStoreDTO;
import com.bookory.dto.response.OrderDetailResponseDTO;
import com.bookory.dto.response.OrderResponseDTO;
import com.bookory.dto.response.OrderResponseForStore;
import com.bookory.entity.Book;
import com.bookory.entity.Cart;
import com.bookory.entity.OrderDetailEntity;
import com.bookory.entity.OrderEntity;
import com.bookory.entity.User;
import com.bookory.object.EmailProvider;
import com.bookory.repository.BookRepository;
import com.bookory.repository.CartRepository;
import com.bookory.repository.OrderDetailRepository;
import com.bookory.repository.OrderRepository;
import com.bookory.repository.StoreRepository;
import com.bookory.repository.UserRepository;

@Service
public class OrderServices {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	OrderDetailRepository orderDetailRepositoy;
	@Autowired
	OrderConvert orderConvert;
	@Autowired
	VnpayService vnpayService;
	@Autowired
	OrderDetailConvert orderDetailConvert;
	@Autowired
	EmailProvider emailProvider ;
	
	public List<OrderResponseForStore> getOrdersByStoreId(long storeId) throws IOException {
		List<OrderEntity> orderEntities =  orderRepository.findByStoreEntityId(storeId);
		return orderConvert.toOrderResponseForStore(orderEntities);
	}

	public OrderResponseForStore getOrdersById(long id) throws IOException {
		OrderEntity orderEntity =  orderRepository.findById(id).orElse(null);
		if(orderEntity != null) {
			//Tìm kiếm tất cả các chi tiết sản đơn hàng của đơn hàng
			List<OrderDetailEntity> orderDetailEntities = orderDetailRepositoy.findByOrderEntityId(orderEntity.getId());
			//Mapping các dữ liệu chi tiết đơn hàng cần thiết
			List<OrderDetailResponseDTO> orderDetailResponses = orderDetailConvert.toOrderDetailResponseDTO(orderDetailEntities);
			//Tạo một đối tượng gồm đơn hàng và các chi tiết đơn hàng nằm trong đơn hàng
			OrderResponseForStore orderResponseForStore =orderConvert.toOrderResponseForStore(orderEntity);
			orderResponseForStore.setOrderDetails(orderDetailResponses);
			return orderResponseForStore;
		}else
			return null;
	}
	
	public void addNewOrder(OrderRequestDTO orderRequest) {
		//Tìm các cửa hàng trong các sản phẩm được chọn
		List<OrderStoreDTO> orderStores = orderRequest.getStores();
		OrderRecipient orderRecipient = orderRequest.getRecipient();
		//Với mỗi cửa hàng thì tạo một đơn hàng
		for (OrderStoreDTO orderStore : orderStores) {
			OrderEntity orderEntity = createOrderEntity(orderRequest, orderStore, orderRecipient);
			long orderId = generateOrderId();
			orderEntity.setId(orderId);
			orderEntity.setTotalMoney(orderStore.getTotalMoney());
			orderEntity.setTransportFee(orderStore.getTransportFee());
			orderRepository.save(orderEntity);
			for (Long id : orderStore.getCartIds()){
				OrderDetailEntity orderDetailEntity = createOrderDetail(orderEntity, id);
				orderDetailRepositoy.save(orderDetailEntity);

				cartRepository.deleteById(id);
			}
			sendMail(orderRequest.getRecipient().getUserId(), orderId, 0);
		}
	}
	public List<OrderResponseDTO> getOrderByUserId(long userId) throws IOException {
		List<OrderEntity> orderEntities = orderRepository.findByUserEntityId(userId);
		if(orderEntities.size() > 0) {
			List<OrderResponseDTO> orderResponseDTOs = new ArrayList<>();
			for(OrderEntity orderEntity:orderEntities) {
				OrderResponseDTO order = orderConvert.toOrderResponseDTO(orderEntity);
				List<OrderDetailEntity> orderDetailEntities = orderDetailRepositoy.findByOrderEntityId(orderEntity.getId());
				System.out.println(orderDetailEntities.size());
				List<OrderDetailResponseDTO> orderDetailResponses = orderDetailConvert.toOrderDetailResponseDTO(orderDetailEntities);
				order.setOrderDetails(orderDetailResponses);
				orderResponseDTOs.add(order);
			}
			return orderResponseDTOs;
		}else
			return null;
		
	}
	public OrderEntity updateOrderStatus(long id, int status) {
		OrderEntity orderEntity = orderRepository.findById(id).get();
		orderEntity.setStatus(status);
		sendMail(orderEntity.getUserEntity().getId(), id, status);
		return orderRepository.save(orderEntity);
	}

	private OrderDetailEntity createOrderDetail(OrderEntity orderEntity, long id) {
		Cart cartEntity = cartRepository.findById(id).get();
		OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
		Book bookEntity = bookRepository.findById(cartEntity.getBookEntity().getId()).get();
		orderDetailEntity.setOrderEntity(orderEntity);
		orderDetailEntity.setBookEntity(bookEntity);
		orderDetailEntity.setAmount(cartEntity.getAmount());
		int discount = bookEntity.getPromotionEntity() == null ? 0 : bookEntity.getPromotionEntity().getDiscount();
		orderDetailEntity.setDiscount(discount);
		orderDetailEntity.setPrice(bookEntity.getPrice()-(bookEntity.getPrice()*discount/100));
		return orderDetailEntity;

	}

	public OrderEntity createOrderEntity(OrderRequestDTO orderRequest, OrderStoreDTO orderStore,
			OrderRecipient orderRecipient) {
		OrderEntity orderEntity = new OrderEntity();
		
		orderEntity.setUserEntity(userRepository.findById(orderRecipient.getUserId()).get());
		orderEntity.setStoreEntity(storeRepository.findById(orderStore.getId()).get());
		orderEntity.setName(orderRecipient.getName());
		orderEntity.setPhone(orderRecipient.getPhone());
		orderEntity.setAddress(orderRecipient.getAddress());
		orderEntity.setPayment(orderRequest.getPayment());
		orderEntity.setNote(orderStore.getNote());
		orderEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		orderEntity.setStatus(0);
		return orderEntity;
	}
	
	public void sendMail(long userId, long orderId, int status) {
		User userEntity = userRepository.findById(userId).orElse(null);
		if(userEntity != null) {
			String mailTo = userEntity.getEmail();	
			String subject = "bookory-Thông báo đơn hàng";
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
	
	 public long generateOrderId() {
		 long uniqueId = (long) Math.floor(((Math.random() * 1000000000) + 100000));
	        return uniqueId;
	    }
}
