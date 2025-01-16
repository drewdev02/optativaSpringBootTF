package org.adrewdev.optativatf.repository;

import org.adrewdev.optativatf.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT b FROM BookEntity b WHERE b.customer.id = :customerId")
    List<BookEntity> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT b FROM BookEntity b WHERE b.category.id = :categoryId")
    List<BookEntity> findByCategoryId(Long categoryId);

    @Query("SELECT b FROM BookEntity b WHERE b.author = :author")
    Optional<BookEntity> findByAuthor(String author);
}
