// BorrowingRecordService.java
package com.example.librarymanagement.service;

import com.example.librarymanagement.DTO.BorrowingRecordDTO;
import com.example.librarymanagement.Mapper.BorrowingRecordMapper;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.BorrowingRecord;
import com.example.librarymanagement.model.Patron;
import com.example.librarymanagement.repository.BorrowingRecordRepository;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    // Create a new borrowing record
    public BorrowingRecordDTO createBorrowingRecord(Long bookId, Long patronId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Patron> optionalPatron = patronRepository.findById(patronId);

        if (optionalBook.isPresent() && optionalPatron.isPresent()) {
            Book book = optionalBook.get();
            Patron patron = optionalPatron.get();

            // Check if the book is already borrowed (business rule: one copy per book)
            if (book.getBorrowingRecords().stream().anyMatch(record -> record.getReturnDate() == null)) {
                throw new IllegalArgumentException("The book is currently borrowed by someone else.");
            }

            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBook(book);
            borrowingRecord.setPatron(patron);
            borrowingRecord.setBorrowDate(LocalDate.now());

            BorrowingRecord savedRecord = borrowingRecordRepository.save(borrowingRecord);

            return BorrowingRecordMapper.toDTO(savedRecord);
        } else {
            throw new IllegalArgumentException("Book or Patron not found.");
        }
    }

    // Retrieve all borrowing records
    public List<BorrowingRecordDTO> getAllBorrowingRecords() {
        List<BorrowingRecord> records = borrowingRecordRepository.findAll();
        return records.stream()
                .map(BorrowingRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a borrowing record by ID
    public Optional<BorrowingRecordDTO> getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id)
                .map(BorrowingRecordMapper::toDTO);
    }

    // Update a borrowing record
    public BorrowingRecordDTO updateBorrowingRecord(Long id, LocalDate returnDate) {
        return borrowingRecordRepository.findById(id)
                .map(record -> {
                    record.setReturnDate(returnDate);
                    BorrowingRecord updatedRecord = borrowingRecordRepository.save(record);
                    return BorrowingRecordMapper.toDTO(updatedRecord);
                })
                .orElseThrow(() -> new IllegalArgumentException("Borrowing record not found with id: " + id));
    }

    // Delete a borrowing record
    public void deleteBorrowingRecord(Long id) {
        borrowingRecordRepository.deleteById(id);
    }
}
