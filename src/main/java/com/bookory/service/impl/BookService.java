package com.bookory.service.impl;

import com.bookory.dto.book.BookFilterDto;
import com.bookory.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bookory.dto.book.BookListDto;
import com.bookory.entity.Book;
import com.bookory.repository.BookRepository;
import com.bookory.repository.BookTagRepository;
import com.bookory.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class BookService extends AbstractService<Book>{

	private final BookRepository bookRepository;
	private final BookTagRepository bookTagRepository;
	private final BookMapper bookMapper;
	private final TagRepository tagRepository;

	public Page<BookListDto> getAllBook(Pageable pageable, BookFilterDto filter) {
		Page<Book> books = getList(pageable, filter);
		return books.map(book -> bookMapper.bookToBookListDto(book));
	}

	@Override
	public Specification<Book> createSpecification(Object filterObject) {
//		BookFilterDto filter = (BookFilterDto)filterObject;
//		return (root, query, builder) -> {
//			if(filter.getId() != null)
//				builder.and(root.get())
//			return builder.and(predicate);
//		};
		return null;
	}

	@Override
	public Page<Book> getData(Pageable pageable, Specification<Book> spec) {
		return null;
	}

//	public List<BookListDto> getBooksByStoreId(long storeId) throws IOException {
//		List<Book> bookEntities = bookRepository.findByStoreEntityId(storeId);
//		List<BookListDto> bookBasicInfors = new ArrayList<>();
//		for(Book bookEntity:bookEntities) {
//			BookListDto bookBasicInfor = bookConvert.toBookBasicInfoDTO(bookEntity);
//			bookBasicInfors.add(bookBasicInfor);
//		}
//		return bookBasicInfors;
//	}
//
//	public List<BookListDto> getBookByKeyword(String key) throws IOException {
//		List<Book> bookEntities = bookRepository
//				.findByNameContainingOrAuthorContainingOrStoreEntityNameContaining(key, key, key);
//		List<BookListDto> bookBasicInfors = new ArrayList<>();
//		for(Book bookEntity:bookEntities) {
//			if(bookEntity.getStatus() != 2) {
//				BookListDto bookBasicInfor = bookConvert.toBookBasicInfoDTO(bookEntity);
//				bookBasicInfors.add(bookBasicInfor);
//			}
//		}
//		return bookBasicInfors;
//	}
//
//	public List<BookExtendInforDTO> getAllBookByStoreIDAndStatus(long storeId, int status) throws IOException {
//		List<Book> bookEntities = bookRepository.findByStoreEntityIdAndStatus(storeId, status);
//		List<BookExtendInforDTO> bookExtendInfors = new ArrayList<>();
//		for(Book bookEntity:bookEntities) {
//			BookExtendInforDTO bookExtendInforDTO = bookConvert.toBookExtendInforDTO(bookEntity);
//			bookExtendInfors.add(bookExtendInforDTO);
//		}
//		return bookExtendInfors;
//	}
//
//	public BookFullInforDTO getBookByID(long id) throws IOException {
//		Book bookEntity = bookRepository.findById(id).orElse(null);
//		if (bookEntity != null) {
//			BookFullInforDTO book = bookConvert.toBookFullInforDTO(bookEntity);
//			List<Tag> tagEntities = bookTagRepository.findByBookId(bookEntity.getId());
//			List<TagResponse> tagResponses = new ArrayList<>();
//			for(Tag tagEntity:tagEntities) {
//				tagResponses.add(new TagResponse(tagEntity.getId(), tagEntity.getName()));
//			}
//			book.setTags(tagResponses);
//			return book;
//		}
//		return null;
//	}
//
//	public Book addNewBook(BookRequestDTO book, MultipartFile[] files) throws IOException {
//		Book bookEntity = bookConvert.toBookEntity(book);
//		if (files != null) {
//			String filePath = imageStorageService.storeMultiFile(files);
//			bookEntity.setImage(filePath);
//		}
//		bookEntity.setCreateDate(Date.valueOf(LocalDate.now()));
//		bookEntity.setQuantitySold(0);
//		bookEntity.setStatus(0);
//		bookEntity = bookRepository.save(bookEntity);
//		List<BookTag> bookTagEntities = new ArrayList<>();
//		for(long id:book.getTagId()) {
//			BookTag booktag = new BookTag();
//			booktag.setBook(bookEntity);
//			booktag.setTag(tagRepository.findById(id).orElse(null));
//			bookTagEntities.add(booktag);
//		}
//		bookTagRepository.saveAll(bookTagEntities);
//		return bookEntity;
//	}
//
//	public Book updateBook(long id, BookRequestDTO book, MultipartFile[] files) throws IOException {
//		Book bookEntity = bookRepository.findById(id).orElse(null);
//		if(bookEntity!=null) {
//			bookEntity = bookConvert.toBookEntity(book);
//			bookRepository.deleteBookTagsByBookId(id);
//			if (files != null) {
//				String imagePaths = imageStorageService.storeMultiFile(files);
//				bookEntity.setImage(imagePaths);
//			}
//			bookEntity.setId(id);
//			bookEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
//			bookEntity =  bookRepository.save(bookEntity);
//			List<BookTag> bookTagEntities = new ArrayList<>();
//			for(long tagid:book.getTagId()) {
//				BookTag booktag = new BookTag();
//				booktag.setBook(bookEntity);
//				booktag.setTag(tagRepository.findById(tagid).orElse(null));
//				bookTagEntities.add(booktag);
//			}
//			bookTagRepository.saveAll(bookTagEntities);
//			return bookEntity;
//		}else
//			return null;
//	}
//
//	public Book setBookStatus(long id, int status) {
//		Book bookEntity = bookRepository.findById(id).get();
//		bookEntity.setStatus(status);
//		return bookRepository.save(bookEntity);
//	}
//
//	public List<BookListDto> getListRelatedBooks(long categoryId) throws IOException {
//		List<Book> bookEntities = bookRepository.findByCategoryEntityIdAndStatus(categoryId, 0);
//		List<BookListDto> bookBasics = new ArrayList<>();
//		for(Book bookEntity: bookEntities) {
//			BookListDto bookBasic = bookConvert.toBookBasicInfoDTO(bookEntity);
//			bookBasics.add(bookBasic);
//		}
//		return bookBasics;
//	}
//
//	public List<BookListDto> getBookForAddNewPromotion(long storeId, Date startDate, Date endDate) {
//		List<Book> bookEntities1 = bookRepository.findBooksByStoreIdAndPromotionIsNull(storeId);
//		List<Book> bookEntities2 = bookRepository.findBooksByStoreEntityIdAndDateNotBetween(storeId, startDate,
//				endDate);
//		bookEntities1.addAll(bookEntities2);
//		List<BookListDto> bookBasics = new ArrayList<>();
//		for(Book bookEntity: bookEntities1) {
//			BookListDto bookBasic = bookConvert.toBookBasicInfoDTO(bookEntity);
//			bookBasics.add(bookBasic);
//		}
//		return bookBasics;
//	}
//
//	public List<BookListDto> getBookForUpdatePromotion(long storeId, long promotioneId) {
//		List<Book> bookEntities = bookRepository.findBookeByStoreEntityIdAndPromotionId(storeId, promotioneId);
//		for (Book bookEntity : bookEntities) {
//			if (bookEntity.getPromotionEntity() != null) {
//				bookEntity.setStatus(1);
//			} else
//				bookEntity.setStatus(0);
//		}
//		List<BookListDto> bookBasics = new ArrayList<>();
//		for(Book bookEntity: bookEntities) {
//			BookListDto bookBasic = bookConvert.toBookBasicInfoDTO(bookEntity);
//			bookBasics.add(bookBasic);
//		}
//		return bookBasics;
//	}
//
//	public List<BookSold> getBookSoldByStoreId(StoreReportRequest storeRequest){
//		return bookRepository.findTopBooksByStoreIdAndDateRange(storeRequest.getStoreId(), storeRequest.getStartDate(),storeRequest.getEndDate());
//	}
}
