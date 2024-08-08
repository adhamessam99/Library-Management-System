// BorrowingRecordDTO.java
package com.example.librarymanagement.DTO;

import com.example.librarymanagement.model.BorrowingRecord;

import java.time.LocalDate;

public class BorrowingRecordDTO {
    private Long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Long bookId;
    private String bookTitle;
    private Long patronId;
    private String patronName;

    // Constructors
    public BorrowingRecordDTO() {}

    public BorrowingRecordDTO(Long id, LocalDate borrowDate, LocalDate returnDate, Long bookId, String bookTitle, Long patronId, String patronName) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.patronId = patronId;
        this.patronName = patronName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Long getPatronId() {
        return patronId;
    }

    public void setPatronId(Long patronId) {
        this.patronId = patronId;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

}
