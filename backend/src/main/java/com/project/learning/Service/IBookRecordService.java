package com.project.learning.Service;

import com.project.learning.Domain.Book;
import com.project.learning.Domain.BookRecord;

import java.util.Date;
import java.util.List;

public interface IBookRecordService {
    void borrowRecord(Long member_id,String book_title);
    BookRecord returnRecord(Long book_id);
    List<BookRecord> getAllBookRecord();
    public BookRecord getBookRecordByDate (Date date);
    public List<BookRecord> sortBookRecordByDate();
}
