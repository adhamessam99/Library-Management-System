// src/main/java/com/example/librarymanagement/controller/PatronController.java
package com.example.librarymanagement.controller;

import com.example.librarymanagement.DTO.PatronDTO;
import com.example.librarymanagement.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    // Retrieve all patrons
    @GetMapping
    public ResponseEntity<List<PatronDTO>> getAllPatrons() {
        List<PatronDTO> patrons = patronService.getAllPatrons();
        return ResponseEntity.ok(patrons);
    }

    // Retrieve a patron by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(@PathVariable Long id) {
        return patronService.getPatronById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new patron
    @PostMapping
    public ResponseEntity<PatronDTO> createPatron(@RequestBody PatronDTO patronDTO) {
        try {
            PatronDTO createdPatronDTO = patronService.createPatron(patronDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPatronDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Update an existing patron
    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable Long id, @RequestBody PatronDTO updatedPatronDTO) {
        try {
            PatronDTO patronDTO = patronService.updatePatron(id, updatedPatronDTO);
            return ResponseEntity.ok(patronDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a patron
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        try {
            patronService.deletePatron(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
