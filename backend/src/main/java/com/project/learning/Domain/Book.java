package com.project.learning.Domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")

public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long bookId; // Auto-increment primary key

    @NonNull
    @Column(name = "book_title", nullable = false, length = 150)
    private String bookTitle;

    @NonNull
    @Column(name = "book_author", nullable = false, length = 100)
    private String bookAuthor;

    @Column(name = "book_publisher", length = 100)
    private String bookPublisher;

    @Column(name = "book_genre", length = 50)
    private String bookGenre;

    @Column(name = "book_publication_year")
    private Integer bookPublicationYear;

    @Column(name = "book_language", length = 50)
    private String bookLanguage;

    @NonNull
    @Column(name = "book_price", nullable = false)
    private Double bookPrice;

    @Column(name = "book_stock_quantity")
    private Integer bookStockQuantity;

    @Column(name = "book_pages")
    private Integer bookPages;

    @Column(name = "book_isbn", length = 13, unique = true)
    private String bookIsbn;

    @Column(name= "book_status", length= 20)
    private String book_status;

}
