// src/main/java/com/example/librarymanagement/mapper/PatronMapper.java
package com.example.librarymanagement.Mapper;

import com.example.librarymanagement.DTO.PatronDTO;
import com.example.librarymanagement.model.Patron;

public class PatronMapper {

    public static PatronDTO toDTO(Patron patron) {
        if (patron == null) {
            return null;
        }

        return new PatronDTO(
                patron.getId(),
                patron.getName(),
                patron.getContactInfo()
        );
    }

    public static Patron toEntity(PatronDTO patronDTO) {
        if (patronDTO == null) {
            return null;
        }

        Patron patron = new Patron();
        patron.setId(patronDTO.getId());
        patron.setName(patronDTO.getName());
        patron.setContactInfo(patronDTO.getContactInfo());

        return patron;
    }
}
