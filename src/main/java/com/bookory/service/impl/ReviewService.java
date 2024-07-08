package com.bookory.service.impl;


import com.bookory.dto.review.ReviewFilterDto;
import com.bookory.dto.review.ReviewListDto;
import com.bookory.dto.review.ReviewSaveDto;
import com.bookory.mapper.ReviewMapper;
import com.bookory.service.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookory.repository.OrderDetailRepository;
import com.bookory.repository.ReviewRepository;
import com.bookory.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
	private final ReviewRepository reviewRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final UserRepository userRepository;
	private final ReviewMapper reviewConvert;

	
	public Long createNewView(ReviewSaveDto reviewDto) {
		return null;
	}


	public Page<ReviewListDto> getReviewList(Pageable pageable, ReviewFilterDto filter) {
		return null;
	}
}
