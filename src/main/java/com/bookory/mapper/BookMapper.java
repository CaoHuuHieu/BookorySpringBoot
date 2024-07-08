package com.bookory.mapper;

import com.bookory.dto.book.BookListDto;
import com.bookory.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookListDto bookToBookListDto(Book book);

}
