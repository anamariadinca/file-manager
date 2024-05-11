package com.thesis.filemanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
}