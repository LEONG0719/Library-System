package com.project.learning.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="BookRecord")
public class BookRecord {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long bookRecord_id;

    @ManyToOne //because each record is owned by one member only, and many record can own by the member
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;

    @Column(name= "borrow_date",nullable = false)
    private LocalDate borrow_date;

    @Column(name= "return_date")
    private LocalDate return_date;

    @Column(name= "bookRecord_status")
    private String bookRecord_status;
}
