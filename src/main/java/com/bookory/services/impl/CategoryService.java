package com.bookory.services.impl;

import com.bookory.dto.category.*;
import com.bookory.entity.Category;
import com.bookory.exception.EntityNotFoundException;
import com.bookory.mapper.CategoryMapper;
import com.bookory.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService extends AbstractService<Category> implements ICategoryService {

	private final CategoryRepository categoryRepository;
	private final ImageStorageService imageStorageService;
	private final CategoryMapper categoryMapper;


	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<CategoryListDto> getCategoryList(Pageable pageable, CategoryFilterDto filter){
		return null;
	}

	private Category getCategoryById(Long id){
		return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("error.entity_not_found", id));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CategoryDetailDto getCategoryDetail(Long id){
		Category category = getCategoryById(id);
		return categoryMapper.categoryToCategoryDetailDto(category);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long createNewCategory(CategorySaveDto categoryDto, MultipartFile file){
		Category category = new Category();
		category.setName(categoryDto.getName());
		if(file != null) {
			String fileName = imageStorageService.storeFile(file);
			category.setThumbnail(fileName);
		}
		category.setStatus(0);
		categoryRepository.save(category);
		return category.getId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long updateCategory(Long id, CategoryUpdateDto categoryDto, MultipartFile file){
		Category category = getCategoryById(id);
		category.setName(categoryDto.getName());
		if(file != null) {
			String fileName=imageStorageService.storeFile(file);
			category.setThumbnail(fileName);
		}
		categoryRepository.save(category);
		return id;
	}

	public Long deleteCategory(Long id){
		Category category = getCategoryById(id);
		category.setStatus(1);
		categoryRepository.save(category);
		return id;
	}

	@Override
	public Specification<Category> createSpecification(Object filter) {
		return null;
	}

	@Override
	public Page<Category> getData(Pageable pageable, Specification<Category> spec) {
		return null;
	}
}
