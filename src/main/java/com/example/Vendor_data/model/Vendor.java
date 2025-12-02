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

    // ➕ New field added
    private String image;

    // Constructors
    public Vendor() {}

    public Vendor(String name, String contactNumber, String university, String image) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.university = university;
        this.image = image;
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

    // ➕ Getter & Setter for image
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
