package com.bookory.services;

import com.bookory.dto.store.StoreDetailDto;
import com.bookory.dto.store.StoreListDto;
import com.bookory.dto.store.StoreSaveDto;
import com.bookory.dto.store.StoreUpdateDto;
import com.bookory.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface IStoreService {
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<StoreListDto> getAllStore(Pageable pageable, String filter);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    StoreDetailDto getStoreDetail(Long id);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Long createNewStore(StoreSaveDto storeDto);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Long updateStoreInformation(Long id, StoreUpdateDto storeDto);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Long activeStore(Long id, Integer active);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Store registerService(Long storeId, Long serviceId);
}
