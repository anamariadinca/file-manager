package com.thesis.filemanager.filetypes.pdf;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "docs")
public class PdfFile {

    @Id
    private String id;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    private String name;

    private String userGuid;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
}

