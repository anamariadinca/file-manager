package com.thesis.filemanager.filetypes.pictures;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "picture_metadata")
public class PictureMetadata {

    @Id
    String id;

    String name;

    String userGuid;

    LocalDate dateUploaded;

    int size;

    @Enumerated(EnumType.STRING)
    PictureType pictureType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setUserGuid(String userUuid) {
        this.userGuid = userUuid;
    }

    public LocalDate getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(LocalDate dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public PictureType getPictureType() {
        return pictureType;
    }

    public void setPictureType(PictureType pictureType) {
        this.pictureType = pictureType;
    }
}
