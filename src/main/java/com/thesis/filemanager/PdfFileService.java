package com.thesis.filemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PdfFileService {

    @Autowired
    private PdfFileRepository pdfFileRepository;

    public List<PdfFile> getAllPdfFiles() {
        return pdfFileRepository.findAll();
    }

    public PdfFile savePdfFile(MultipartFile file) {
        PdfFile pdfFile = new PdfFile();
        pdfFile.setFilename(file.getOriginalFilename());
        try {
            pdfFile.setContent(file.getBytes());
            pdfFileRepository.save(pdfFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfFile;
    }

    public PdfFile getPdfFileById(Long id) {
        return pdfFileRepository.findById(id).orElseThrow(() -> new PdfFileNotFoundException("PdfFile not found with id " + id));
    }

    public void deletePdfFile(Long id) {
        pdfFileRepository.deleteById(id);
    }
}