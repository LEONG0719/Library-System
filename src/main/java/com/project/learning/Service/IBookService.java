package com.project.learning.Service;

import com.project.learning.Domain.Book;

import java.util.List;

public interface IBookService {
    Book addNewBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long book_id);
    int updateBook(double price, Long id);
    void deleteBook(Long id);
}
