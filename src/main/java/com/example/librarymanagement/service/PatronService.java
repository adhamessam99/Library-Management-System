// src/main/java/com/example/librarymanagement/service/PatronService.java
package com.example.librarymanagement.service;

import com.example.librarymanagement.DTO.PatronDTO;
import com.example.librarymanagement.Mapper.PatronMapper;
import com.example.librarymanagement.model.Patron;
import com.example.librarymanagement.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    // Create a new patron
    @Transactional
    public PatronDTO createPatron(PatronDTO patronDTO) {
        Patron patron = PatronMapper.toEntity(patronDTO);
        Patron savedPatron = patronRepository.save(patron);
        return PatronMapper.toDTO(savedPatron);
    }

    // Retrieve all patrons
    @Transactional
    public List<PatronDTO> getAllPatrons() {
        return patronRepository.findAll().stream()
                .map(PatronMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a patron by ID
    @Transactional
    public Optional<PatronDTO> getPatronById(Long id) {
        return patronRepository.findById(id)
                .map(PatronMapper::toDTO);
    }

    // Update an existing patron
    @Transactional
    public PatronDTO updatePatron(Long id, PatronDTO updatedPatronDTO) {
        return patronRepository.findById(id)
                .map(existingPatron -> {
                    existingPatron.setName(updatedPatronDTO.getName());
                    existingPatron.setContactInfo(updatedPatronDTO.getContactInfo());
                    Patron updatedPatron = patronRepository.save(existingPatron);
                    return PatronMapper.toDTO(updatedPatron);
                })
                .orElseThrow(() -> new IllegalArgumentException("Patron not found"));
    }

    // Delete a patron
    public void deletePatron(Long id) {
        if (!patronRepository.existsById(id)) {
            throw new IllegalArgumentException("Patron not found");
        }
        patronRepository.deleteById(id);
    }
}
