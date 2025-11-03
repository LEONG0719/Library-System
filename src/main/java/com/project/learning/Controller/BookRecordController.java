package com.project.learning.Controller;

import com.project.learning.Domain.BookRecord;
import com.project.learning.Service.IBookRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/BookRecord")
public class BookRecordController {

    @Autowired
    IBookRecordService bookRecordService;

    @PostMapping("/borrowRecord/{member_id}/{book_title}")
    public void borrowRecord(@PathVariable Long member_id,@PathVariable String book_title){
        bookRecordService.borrowRecord(member_id,book_title);
    }

    @PutMapping("/returnRecord/{book_id}")
    public BookRecord returnRecord(@PathVariable Long book_id) {
        return bookRecordService.returnRecord(book_id);
    }

    @GetMapping("/getAllBookRecord")
    public List<BookRecord> getAllBookRecord(){
        return bookRecordService.getAllBookRecord();
    }

    @GetMapping("/getBookRecordByDate/{date}")
    public BookRecord getBookRecordByDate (@PathVariable Date date){
        return bookRecordService.getBookRecordByDate(date);
    }

    @GetMapping("/sortBookRecordByDate")
    public List<BookRecord> sortBookRecordByDate(){
        return bookRecordService.sortBookRecordByDate();
    }
}
