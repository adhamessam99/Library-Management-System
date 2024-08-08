// src/main/java/com/example/librarymanagement/service/BookService.java
package com.example.librarymanagement.service;
import org.springframework.transaction.annotation.Transactional;

import com.example.librarymanagement.DTO.BookDTO;
import com.example.librarymanagement.Mapper.BookMapper;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Create a new book
    @Transactional
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDTO(savedBook);
    }

    // Retrieve all books
    @Transactional
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a book by ID
    @Transactional
    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::toDTO);
    }

    // Update an existing book
    @Transactional
    public BookDTO updateBook(Long id, BookDTO updatedBookDTO) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatedBookDTO.getTitle());
                    existingBook.setAuthor(updatedBookDTO.getAuthor());
                    existingBook.setPublicationYear(updatedBookDTO.getPublicationYear());
                    existingBook.setIsbn(updatedBookDTO.getIsbn());
                    Book updatedBook = bookRepository.save(existingBook);
                    return BookMapper.toDTO(updatedBook);
                })
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    // Delete a book
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}
