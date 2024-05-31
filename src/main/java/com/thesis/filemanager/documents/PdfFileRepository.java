package com.thesis.filemanager.documents;

import com.thesis.filemanager.documents.models.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
}

