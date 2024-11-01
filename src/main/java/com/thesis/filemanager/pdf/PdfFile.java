package com.thesis.filemanager.pdf;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pdf_files")
public class PdfFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    private String name;

    // Getters and Setters
}

