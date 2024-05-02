package com.thesis.filemanager;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf-files")
public class PdfFileController {

    private final PdfFileService pdfFileService;

    public PdfFileController(PdfFileService pdfFileService) {
        this.pdfFileService = pdfFileService;
    }

    @PostMapping
    public ResponseEntity<String> uploadPdfFile(@RequestParam("file") MultipartFile file) {
        try {
            PdfFile pdfFile = pdfFileService.savePdfFile(file);
            return ResponseEntity.ok("File uploaded successfully with ID: " + pdfFile.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getPdfFile(@PathVariable Long id) {
        PdfFile pdfFile = pdfFileService.getPdfFileById(id);
        if (pdfFile == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] pdfContent = pdfFile.getContent();
        Resource resource = new ByteArrayResource(pdfContent);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePdfFile(@PathVariable Long id) {
        PdfFile pdfFile = pdfFileService.getPdfFileById(id);
        if (pdfFile == null) {
            return ResponseEntity.notFound().build();
        }

        pdfFileService.deletePdfFile(id);
        return ResponseEntity.ok("File deleted successfully");
    }
}


