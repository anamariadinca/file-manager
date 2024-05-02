package com.thesis.filemanager;

import com.thesis.filemanager.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
}

