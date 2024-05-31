package com.thesis.filemanager.documents.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PDF_FILES")
public class PdfFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    private String name;

}

