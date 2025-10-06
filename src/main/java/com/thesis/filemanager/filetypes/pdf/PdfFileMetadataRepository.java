package com.thesis.filemanager.filetypes.pdf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PdfFileMetadataRepository extends JpaRepository<PdfFileMetadata, String> {
    List<PdfFileMetadata> findByUserGuid(String guid);
    List<PdfFileMetadata> findByUserGuidAndFavorite(String userGuid, boolean favorite);
}

