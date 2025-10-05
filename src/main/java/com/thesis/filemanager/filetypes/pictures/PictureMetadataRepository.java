package com.thesis.filemanager.filetypes.pictures;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureMetadataRepository extends JpaRepository<PictureMetadata, String> {
    List<PictureMetadata> findByUserGuid(String guid);

    List<PictureMetadata> findByUserGuidAndPictureType(String guid, PictureType pictureType);
}

