package com.bookory.service.impl;


import com.bookory.dto.tag.TagFilterDto;
import com.bookory.dto.tag.TagSaveDto;
import com.bookory.dto.tag.TagUpdateDto;
import com.bookory.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookory.entity.Tag;
import com.bookory.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {

	TagRepository tagRepository;
	
	public Page<Tag> getALlTag(Pageable pageable, TagFilterDto filter){
		return null;
	}

	public Tag getTagByID(long id){
		return tagRepository.findById(id).orElse(null);
	}
	
	public Long createNewTag(TagSaveDto tagSaveDto){
		return null;
	}
	
	public Long updateTag(Long id, TagUpdateDto tagDto){
		return null;
	}
	public void deleteTag(Long tagId){
		tagRepository.deleteById(tagId);
	}
}
