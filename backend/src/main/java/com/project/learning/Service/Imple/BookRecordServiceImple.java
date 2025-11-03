package com.project.learning.Service.Imple;


import com.project.learning.Domain.Book;
import com.project.learning.Domain.BookRecord;
import com.project.learning.Domain.Member;
import com.project.learning.Repo.BookRecordRepo;
import com.project.learning.Repo.BookRepo;
import com.project.learning.Repo.MemberRepo;
import com.project.learning.Service.IBookRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BookRecordServiceImple implements IBookRecordService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookRecordRepo bookRecordRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Override
    public void borrowRecord(Long member_id,String book_title){
        Book book = bookRepo.findByBookTitle(book_title)
                .orElseThrow(()->new RuntimeException("Book not found with title: " + book_title));
        Member member = memberRepo.findById(member_id)
                .orElseThrow(()->new RuntimeException("Member not found with id: " + member_id));
        if("Unavailable".equals(book.getBook_status())) {
            throw new RuntimeException("Book is unavailable or be borrowed");
        }
         book.setBook_status("Unavailable");
         bookRepo.save(book);

         BookRecord bookRecord = new BookRecord();
         bookRecord.setBook(book);
         bookRecord.setMember(member);
         bookRecord.setBookRecord_status("borrowed");
         bookRecord.setBorrow_date(LocalDate.now());
         bookRecord.setReturn_date(LocalDate.now().plusDays(7));

         bookRecordRepo.save(bookRecord);
    }

    @Override
    public BookRecord returnRecord(Long book_id){
        Book book = bookRepo.findById(book_id)
            .orElseThrow(()->new RuntimeException("Book not found with id: " + book_id));
        book.setBook_status("Available");
        bookRepo.save(book);

        BookRecord bookRecord = bookRecordRepo.findByBookId(book_id);
        bookRecord.setBookRecord_status("returned");
        return bookRecordRepo.save(bookRecord);
    }

    @Override
    public List<BookRecord> getAllBookRecord(){
        return bookRecordRepo.findAll();
    }

    @Override
    public BookRecord getBookRecordByDate (Date date){
        return bookRecordRepo.findByDate(date)
                .orElseThrow(()->new RuntimeException("BookRecord not found with date: " + date));

    }

    @Override
    public List<BookRecord> sortBookRecordByDate() {
        return bookRecordRepo.findAll(Sort.by(Sort.Direction.ASC, "borrow_date"));

    }



}
