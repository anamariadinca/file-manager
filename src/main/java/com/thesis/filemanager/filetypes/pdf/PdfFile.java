package com.thesis.filemanager.filetypes.pdf;

import com.thesis.filemanager.filetypes.selfie.SelfiePictureMetadata;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.time.LocalDate;


@Document(collection = "docs")
public class PdfFile {

    @Id
    private String id;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    @DBRef
    private PdfFileMetadata pdfFileMetadata;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public PdfFileMetadata getPdfFileMetadata() {
        return pdfFileMetadata;
    }

    public void setPdfFileMetadata(PdfFileMetadata pdfFileMetadata) {
        this.pdfFileMetadata = pdfFileMetadata;
    }
}

