package com.bookory.service.impl;

import com.bookory.dto.slide.SlideFilterDto;
import com.bookory.dto.slide.SlideListDto;
import com.bookory.dto.slide.SlideSaveDto;
import com.bookory.dto.slide.SlideUpdateDto;
import com.bookory.service.ISlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.bookory.repository.SlideRepository;

@Service
@RequiredArgsConstructor
public class SlideService implements ISlideService {

	SlideRepository slideRepository;

	public Long createNewSlide(SlideSaveDto slideDto, MultipartFile file) {
		return null;
	}
	
	public void deleteSlide(Long slideId) {
		slideRepository.deleteById(slideId);
	}
	
	public void updateStatus(Long slideId, SlideUpdateDto slideUpdateDto) {

	}
	public Page<SlideListDto> getAllSlide(Pageable pageable, SlideFilterDto filter) {
		return null;
	}

}
