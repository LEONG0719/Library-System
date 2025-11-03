package com.project.learning.Repo;

import com.project.learning.Domain.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepo extends JpaRepository <Book, Long>{

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE book SET book_price = :book_price WHERE book_id = :book_id")
    int updateBookByID (@Param("book_price")double price, @Param("book_id")Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value= "DELETE FROM book WHERE book_id = :book_id")
    int deleteBook(@Param("book_id")Long id);

    @Query(nativeQuery = true, value= "SELECT * FROM book WHERE book_title = :book_title")
    Optional<Book> findByBookTitle(@Param("book_title")String book_title);

}
