package com.project.learning.Service.Imple;

import com.project.learning.Domain.Book;
import com.project.learning.Repo.BookRepo;
import com.project.learning.Service.IBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImple implements IBookService {

    @Autowired
    private BookRepo bookrepository;

    @Override
    public Book addNewBook(Book book) {
        if(book == null){
            throw new IllegalArgumentException("hi");
        }
        if(book.getBookAuthor().isBlank()){
            throw new IllegalArgumentException("Author cannot be blank");
        }
        if(book.getBookTitle().isBlank()){
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if(book.getBookPrice() <= 0){
            throw new IllegalArgumentException("Book price must greater than 0");
        }

        if (book.getBook_status() == null || book.getBook_status().isBlank()) {
            book.setBook_status("Available");
        }
        return bookrepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookrepository.findAll();
    }

    @Override
    public Book getBookById(Long book_id) {
        return bookrepository.findById(book_id).orElseThrow(()->new RuntimeException("Book not found with id: " + book_id));
    }

    @Override
    public int updateBook(double price, Long id) {
        return bookrepository.updateBookByID(price,id);
    }

    @Override
    public void deleteBook(Long id) {
        bookrepository.deleteBook(id);
    }
}
