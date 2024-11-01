package com.thesis.filemanager.institution;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "public_institutions")
public class PublicInstitution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uniqueIdentification;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    // Getters and Setters

    // Constructor
}

