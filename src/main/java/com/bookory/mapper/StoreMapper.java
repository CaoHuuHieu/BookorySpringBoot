package com.bookory.mapper;

import com.bookory.dto.store.StoreDetailDto;
import com.bookory.dto.store.StoreListDto;
import com.bookory.dto.store.StoreSaveDto;
import com.bookory.dto.store.StoreUpdateDto;
import com.bookory.entity.Store;
import org.mapstruct.Mapper;

@Mapper
public interface StoreMapper {
    StoreListDto storeToStoreListDto(Store store);

    StoreDetailDto storeToStoreDetailDto(Store store);

    Store storeSaveDtoToStore(StoreSaveDto store);

    Store storeUdpateDtoToStore(Store store, StoreUpdateDto storeDto);
}
