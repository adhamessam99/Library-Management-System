// BorrowingRecordController.java
package com.example.librarymanagement.controller;

import com.example.librarymanagement.DTO.BorrowingRecordDTO;
import com.example.librarymanagement.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    // Allow a patron to borrow a book
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            BorrowingRecordDTO borrowingRecord = borrowingRecordService.createBorrowingRecord(bookId, patronId);
            return ResponseEntity.ok(borrowingRecord);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Record the return of a borrowed book by a patron
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        Optional<BorrowingRecordDTO> optionalBorrowingRecord = borrowingRecordService.getAllBorrowingRecords().stream()
                .filter(record -> record.getBookId().equals(bookId) &&
                        record.getPatronId().equals(patronId) &&
                        record.getReturnDate() == null)
                .findFirst();

        if (optionalBorrowingRecord.isPresent()) {
            BorrowingRecordDTO borrowingRecord = optionalBorrowingRecord.get();
            borrowingRecord.setReturnDate(LocalDate.now());
            BorrowingRecordDTO updatedRecord = borrowingRecordService.updateBorrowingRecord(borrowingRecord.getId(), borrowingRecord.getReturnDate());
            return ResponseEntity.ok(updatedRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Retrieve all borrowing records
    @GetMapping("/borrowing-records")
    public ResponseEntity<List<BorrowingRecordDTO>> getAllBorrowingRecords() {
        List<BorrowingRecordDTO> records = borrowingRecordService.getAllBorrowingRecords();
        return ResponseEntity.ok(records);
    }

    // Retrieve a borrowing record by ID
    @GetMapping("/borrowing-records/{id}")
    public ResponseEntity<BorrowingRecordDTO> getBorrowingRecordById(@PathVariable Long id) {
        return borrowingRecordService.getBorrowingRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a borrowing record
    @DeleteMapping("/borrowing-records/{id}")
    public ResponseEntity<Void> deleteBorrowingRecord(@PathVariable Long id) {
        borrowingRecordService.deleteBorrowingRecord(id);
        return ResponseEntity.noContent().build();
    }
}
