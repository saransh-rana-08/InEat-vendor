package com.example.Vendor_data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactNumber;
    private String university;

    // Constructors
    public Vendor() {}

    public Vendor(String name, String contactNumber, String university) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.university = university;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }
}
