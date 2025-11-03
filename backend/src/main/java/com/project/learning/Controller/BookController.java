package com.project.learning.Controller;

import com.project.learning.Domain.Book;
import com.project.learning.Service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/Book")

public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping("/addNewBook")
    public Book addNewBook(@RequestBody Book book){
        return bookService.addNewBook(book);
    }

    @GetMapping("/getAllBook")
    public List<Book> getAllBook(){
        return bookService.getAllBooks();
    }

    @GetMapping("/getBookById/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PutMapping("/updateBook")
    public int updateBookById(double price, Long id){
        return bookService.updateBook(price,id);
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBookById(@PathVariable Long id){
        bookService.deleteBook(id);
        return "Book delete successfully";
    }

}
