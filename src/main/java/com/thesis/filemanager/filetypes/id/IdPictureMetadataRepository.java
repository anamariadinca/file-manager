package com.thesis.filemanager.filetypes.id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdPictureMetadataRepository extends JpaRepository<IdPictureMetadata, Long> {
}

