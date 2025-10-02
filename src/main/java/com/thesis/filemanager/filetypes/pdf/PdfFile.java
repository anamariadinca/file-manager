package com.thesis.filemanager.filetypes.pdf;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


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

