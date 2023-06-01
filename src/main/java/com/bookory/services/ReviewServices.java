package com.bookory.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookory.EntityAndDto.ReviewConvert;
import com.bookory.dto.request.ReviewRequestDTO;
import com.bookory.dto.response.ReviewResponseDTO;
import com.bookory.entity.BookEntity;
import com.bookory.entity.OrderDetailEntity;
import com.bookory.entity.ReviewEntity;
import com.bookory.entity.UserEntity;
import com.bookory.repositories.OrderDetailRepository;
import com.bookory.repositories.ReviewRepository;
import com.bookory.repositories.UserRepository;


@Service
public class ReviewServices {
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	OrderDetailRepository orderDetailRepositoy;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ReviewConvert reviewConvert;

	
	public void addNewReview(ReviewRequestDTO review) {
		List<OrderDetailEntity> orderDetailEntities = orderDetailRepositoy.findByOrderEntityId(review.getOrderId());
		Date createDate = Date.valueOf(LocalDate.now());
		List<ReviewEntity> reviewEntities = new ArrayList<ReviewEntity>();
		for(OrderDetailEntity orderDetailEntity:orderDetailEntities) {
			BookEntity bookEntity = orderDetailEntity.getBookEntity();
			UserEntity userEntity = userRepository.findById(review.getUserId()).get();
			ReviewEntity reviewEntity = new ReviewEntity(userEntity, bookEntity, review.getStar(), review.getComment(),createDate );
			reviewEntities.add(reviewEntity);
		}
		reviewRepository.saveAll(reviewEntities);
	}


	public List<ReviewResponseDTO> getAllReviewByBookId(long id) {
		List<ReviewEntity> reviewEntities = reviewRepository.findByBookEntityId(id);
		if(reviewEntities.size() > 0) {
			System.out.println("review say hi");
			List<ReviewResponseDTO> reviewResponses =reviewConvert.toReviewResponseDTO(reviewEntities);
			return reviewResponses;
		}else
			return null;
	}
}
