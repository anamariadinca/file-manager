package com.thesis.filemanager.filetypes.pictures;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pictures")
public class Picture {

    @Id
    private String id;
    private byte[] content;

    @DBRef
    private PictureMetadata metadata;

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

    public PictureMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PictureMetadata metadata) {
        this.metadata = metadata;
    }
}
