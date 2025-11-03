package com.project.learning.Repo;

import com.project.learning.Domain.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface BookRecordRepo extends JpaRepository<BookRecord, Long> {
    @Query(nativeQuery = true, value= "SELECT * FROM book_record WHERE book_id = :book_id")
    BookRecord findByBookId(@Param("book_id")Long book_id);

    @Query(nativeQuery = true, value= "SELECT * FROM BookRecord WHERE borrow_date= :borrow_date")
    Optional<BookRecord> findByDate(@Param("borrow_date") Date date);
}
