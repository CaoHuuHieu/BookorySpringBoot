package com.bookory.service.impl;

import java.sql.Date;
import java.time.LocalDate;

import com.bookory.dto.store.StoreDetailDto;
import com.bookory.dto.store.StoreListDto;
import com.bookory.dto.store.StoreSaveDto;
import com.bookory.dto.store.StoreUpdateDto;
import com.bookory.entity.SubscriptionPackage;
import com.bookory.exception.EntityNotFoundException;
import com.bookory.mapper.StoreMapper;
import com.bookory.repository.SubscriptionPackageRepository;
import com.bookory.service.IStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookory.entity.Store;
import com.bookory.repository.StoreRepository;
import com.bookory.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class StoreService extends AbstractService<Store> implements IStoreService {
	private final StoreRepository storeRepository;
	private final UserRepository userRepository;
	private final AddressService addressServices;
	private final SubscriptionPackageRepository subscriptionPackageRepository;
	private final StoreMapper storeMapper;


	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<StoreListDto> getAllStore(Pageable pageable, String filter){
		Page<Store> stores = getList(pageable, filter);
		return stores.map(storeMapper::storeToStoreListDto);
	}

	private Store getStoreById(Long id){
		return storeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("error.entity_not_found", id));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StoreDetailDto getStoreDetail(Long id){
		Store store = getStoreById(id);
		return storeMapper.storeToStoreDetailDto(store);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long createNewStore(StoreSaveDto storeDto){
		Store store = storeMapper.storeSaveDtoToStore(storeDto);
		store.setCreateDate(Date.valueOf(LocalDate.now()));
		store.setStatus(0);
		storeRepository.save(store);
		return store.getId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long updateStoreInformation(Long id, StoreUpdateDto storeDto){
		Store store = getStoreById(id);
		store = storeMapper.storeUdpateDtoToStore(store, storeDto);
		store.setUpdateDate(Date.valueOf(LocalDate.now()));
		storeRepository.save(store);
		return store.getId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long activeStore(Long id, Integer active){
		Store store = getStoreById(id);
		store.setStatus(active);
		storeRepository.save(store);
		return id;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Store registerService(Long storeId, Long serviceId) {
		Store store = getStoreById(storeId);
		SubscriptionPackage servicePack = subscriptionPackageRepository.findById(serviceId).orElseThrow(() -> new EntityNotFoundException("error.entity_not_found", "Service Pack", serviceId));
		Date endDate = calculateStoreEndDate(servicePack.getExpirationDate());
		store.setEndDate(endDate);
		return storeRepository.save(store);
	}

	@Override
	public Specification<Store> createSpecification(Object filter) {
		return null;
	}

	@Override
	public Page<Store> getData(Pageable pageable, Specification<Store> spec) {
		return null;
	}

	private Date calculateStoreEndDate(int expirationDate){
		Date sqlDate = Date.valueOf(LocalDate.now());
		long millis = sqlDate.getTime();
		long millisToAdd = expirationDate * 24L * 60L * 60L * 1000L;
		long millisAfterAddingDays = millis + millisToAdd;
		return new Date(millisAfterAddingDays);
	}
}
