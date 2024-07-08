package com.bookory.service.impl;

import java.io.IOException;

import com.bookory.dto.subscription_package.*;
import com.bookory.mapper.SubscriptionPackageMapper;
import com.bookory.repository.SubscriptionPackageRepository;
import com.bookory.service.ISubscriptionPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class SubscriptionPackageService implements ISubscriptionPackageService {

	private final SubscriptionPackageRepository subscriptionPackageRepository;
	private final SubscriptionPackageMapper subscriptionPackageMapper;
	public Page<SubscriptionPackageListDto> getSubscriptionPackageList(Pageable pageable, SubscriptionPackageFilterDto filter) throws IOException{
		return null;
	}

	public SubscriptionPackageDetailDto getSubscriptionPackageDetail(Long id){
		return null;
	}

	public Long createNewSubscriptionService(SubscriptionPackageSaveDto subscriptionPackageSaveDto, MultipartFile file) {
		return null;
	}

	public Long updateSubscriptionPackage(Long id, SubscriptionPackageUpdateDto subscriptionPackageUpdateDto, MultipartFile file) {
		return null;
	}

	public Long deleteService(Long id){
		return null;
	}
}
