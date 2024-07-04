package com.bookory.repository;

import java.sql.Date;
import java.util.List;

import com.bookory.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookory.dto.response.BookSold;
import com.bookory.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

	List<Book> findByStoreEntityId(long storeId);

	List<Book> findByStoreEntityIdAndStatus(long storeId, int status);

	List<Book> findByCategoryEntityIdAndStatus(long id, int status);

	List<Book> findByNameContainingOrAuthorContainingOrStoreEntityNameContaining(String name, String author, String storeName);

	@Query("DELETE FROM BookTagEntity bte WHERE book.id = :bookId")
	void deleteBookTagsByBookId(@Param("bookId") long bookId);

	@Query("UPDATE BookEntity b SET b.promotionEntity = null WHERE b.promotionEntity.Id = :promotionId")
	void setPromotioneNullByPromotioneId(@Param("promotionId") long promotionId);

	@Query("UPDATE BookEntity b SET b.promotionEntity =:promotion  WHERE b.id IN :bookIds")
	void updatePromotionByBookIdInAndPromotionId(List<Long> bookIds, Promotion promotion);
	
	@Query("SELECT b FROM BookEntity b WHERE b.storeEntity.id = :storeId AND b.promotionEntity IS NULL  AND b.status=0")
	List<Book> findBooksByStoreIdAndPromotionIsNull(@Param("storeId") long storeId);

	@Query("SELECT b FROM BookEntity b "
			+ "WHERE b.storeEntity.id = :storeId AND b.status=0 AND ("
			+ "(b.promotionEntity IS NOT NULL AND NOT((b.promotionEntity.startDate between :startDate AND :endDate) OR (b.promotionEntity.endDate between :startDate AND :endDate))"
			+ "))")
	List<Book> findBooksByStoreEntityIdAndDateNotBetween(@Param("storeId") long storeId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query("SELECT b FROM BookEntity b WHERE b.storeEntity.id=:storeId AND (b.promotionEntity IS NULL or b.promotionEntity.id=:promotionId) AND b.status=0")
	List<Book> findBookByStoreEntityIdAndPromotionId(@Param("storeId") long storeId, @Param("promotionId") long promotionId);
	
	List<Book> findByPromotionEntityId(long id);
	
	@Query("SELECT COUNT(b) FROM BookEntity b WHERE b.status = :status")
    Long countByStatus(@Param("status") int status);
	
	@Query("SELECT SUM(b.quantitySold) FROM BookEntity b")
	Long sumQuantitySold();

	@Query("SELECT b.name, SUM(odt.amount) AS totalSold "
			+ "FROM OrderDetailEntity odt "
			+ "JOIN odt.bookEntity b "
			+ "JOIN b.storeEntity s "
			+ "JOIN odt.orderEntity o "
			+ "WHERE s.id = :storeId "
			+ "AND o.createDate BETWEEN :startDate AND :endDate "
			+ "GROUP BY b.id "
			+ "ORDER BY s.id DESC")
	List<BookSold> findTopBooksByStoreIdAndDateRange(
			@Param("storeId") Long storeId,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	List<Book> findByStatus(int i);

}
