package com.thesis.filemanager.filetypes.selfie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfiePictureMetadataRepository extends JpaRepository<SelfiePictureMetadata, Long> {
}

