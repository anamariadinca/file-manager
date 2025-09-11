package com.thesis.filemanager.filetypes.pdf;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PdfFileRepository extends MongoRepository<PdfFile, Long> {
    List<PdfFile> findByUserGuid(String guid);
}

