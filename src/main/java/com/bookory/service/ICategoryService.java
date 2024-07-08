package com.bookory.service;

import com.bookory.dto.category.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ICategoryService {
    Page<CategoryListDto> getCategoryList(Pageable pageable, CategoryFilterDto filter);

    CategoryDetailDto getCategoryDetail(Long id);

    Long createNewCategory(CategorySaveDto categoryDto, MultipartFile file);

    Long updateCategory(Long id, CategoryUpdateDto categoryDto, MultipartFile file);
}
