package com.bookory.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookory.entity.Tag;
import com.bookory.repository.TagRepository;

@Service
public class TagServices {
	@Autowired
	TagRepository tagRepository;
	
	public List<Tag> getALlTag(){
		return tagRepository.findAll();
	}
	public Tag getTagByID(long id){
		return tagRepository.findById(id).orElse(null);
	}
	
	public Tag addNewTag(Tag tagEntity){
		return tagRepository.save(tagEntity);
	}
	
	public Tag updateTag(long id, Tag tagEntity){
		tagEntity.setId(id);
		tagEntity.setId(id);
		return tagRepository.save(tagEntity);
	}
	public void deleteTag(long id){
		tagRepository.deleteById(id);
		
	}
}
