package com.thesis.filemanager.filetypes.id;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "id_pictures")
public class IdPicture {

    @Id
    private String id;
    private byte[] content;

    @DBRef
    private IdPictureMetadata metadata;

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

    public IdPictureMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(IdPictureMetadata metadata) {
        this.metadata = metadata;
    }
}
