package com.bookory.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractService<T> {

    public Page<T> getList(Pageable pageable, Object filter){
        return getData(pageable, createSpecification(filter));
    }

    public abstract Specification<T> createSpecification(Object filter);
    public abstract Page<T> getData(Pageable pageable,  Specification<T> spec);
}
