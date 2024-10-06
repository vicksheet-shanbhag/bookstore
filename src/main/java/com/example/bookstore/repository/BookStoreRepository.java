package com.example.bookstore.repository;

import com.example.bookstore.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookStoreRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    // Query to filter books by category and keyword in id, title, or author
    @Query("SELECT b FROM Book b WHERE " +
            "(:keyword IS NULL OR b.title LIKE %:keyword% OR b.author LIKE %:keyword% " +
            "OR (CAST(:keyword AS long) IS NOT NULL AND b.id = CAST(:keyword AS long))) " +
            "AND (:category IS NULL OR b.category = :category)")

    List<Book> findByKeywordAndCategory(@Param("keyword") String keyword,
                                        @Param("category") String category);

    @Query("SELECT SUM(b.sold) FROM Book b WHERE " +
            "(:keyword IS NULL OR b.title LIKE %:keyword% OR b.author LIKE %:keyword% " +
            "OR (CAST(:keyword AS long) IS NOT NULL AND b.id = CAST(:keyword AS long))) " +
            "AND (:category IS NULL OR b.category = :category)")
    Integer getNumberOfBooksSoldByKeywordAndCategory(@Param("keyword") String keyword,
                                                     @Param("category") String category);
}
