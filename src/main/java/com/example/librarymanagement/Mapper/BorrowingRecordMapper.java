// BorrowingRecordMapper.java
package com.example.librarymanagement.Mapper;

import com.example.librarymanagement.DTO.BorrowingRecordDTO;
import com.example.librarymanagement.model.BorrowingRecord;

public class BorrowingRecordMapper {

    public static BorrowingRecordDTO toDTO(BorrowingRecord borrowingRecord) {
        if (borrowingRecord == null) {
            return null;
        }

        return new BorrowingRecordDTO(
                borrowingRecord.getId(),
                borrowingRecord.getBorrowDate(),
                borrowingRecord.getReturnDate(),
                borrowingRecord.getBook().getId(),
                borrowingRecord.getBook().getTitle(),
                borrowingRecord.getPatron().getId(),
                borrowingRecord.getPatron().getName()
        );
    }
}
