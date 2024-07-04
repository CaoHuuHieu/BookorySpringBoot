package com.bookory.mapper;


import com.bookory.dto.category.CategoryDetailDto;
import com.bookory.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryDetailDto categoryToCategoryDetailDto(Category category);
}
