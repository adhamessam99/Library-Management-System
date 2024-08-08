// PatronDTO.java
package com.example.librarymanagement.DTO;

public class PatronDTO {
    private Long id;
    private String name;
    private String contactInfo;

    // Constructors
    public PatronDTO() {}

    public PatronDTO(Long id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
