// src/main/java/com/example/librarymanagement/mapper/BookMapper.java
package com.example.librarymanagement.Mapper;

import com.example.librarymanagement.DTO.BookDTO;
import com.example.librarymanagement.model.Book;

public class BookMapper {

    public static BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getIsbn()
        );
    }

    public static Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setIsbn(bookDTO.getIsbn());

        return book;
    }
}
