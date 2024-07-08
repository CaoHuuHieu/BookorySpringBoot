package com.bookory.service.impl;

import com.bookory.dto.promotion.*;
import com.bookory.entity.Promotion;
import com.bookory.exception.EntityNotFoundException;
import com.bookory.mapper.PromotionMapper;
import com.bookory.service.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookory.repository.BookRepository;
import com.bookory.repository.PromotionRepository;
import com.bookory.repository.StoreRepository;


@Service
@RequiredArgsConstructor
public class PromotionService implements IPromotionService {

	private final PromotionRepository promotionRepository;
	private final PromotionMapper promotionConvert;
	private final BookRepository bookRepository;
	private final StoreRepository storeRepository;

	public Page<PromotionListDto> getPromotionList(Pageable pageable, PromotionFilterDto filter){
		return null;
	}

	private Promotion getPromotionById(Long id){
		return promotionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("error.entity_not_found", "Promotion", id));
	}

	public PromotionDetailDto getPromotionDetail(Long promotionId){
		return null;
	}
	
	public Long createNewPromotion(PromotionSaveDto promotionDto){
		return null;
	}
	
	public Long updatePromotion(Long promotionId, PromotionUpdateDto promotionDto){
		return null;
	}

	public Long updatePromotionStatus(Long promotionId, int status){
		return null;
	}
}
