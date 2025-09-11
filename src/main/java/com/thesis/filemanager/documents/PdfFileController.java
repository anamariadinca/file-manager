package com.thesis.filemanager.documents;

import com.thesis.filemanager.config.JwtService;
import com.thesis.filemanager.filetypes.pdf.PdfFile;
import com.thesis.filemanager.filetypes.pdf.PdfFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pdf-files")
public class PdfFileController {

    private final PdfFileService pdfFileService;
    private final JwtService jwtService;
    private final Logger log = LoggerFactory.getLogger(PdfFileController.class);

    public PdfFileController(PdfFileService pdfFileService, JwtService jwtService) {
        this.pdfFileService = pdfFileService;
        this.jwtService = jwtService;
    }

    @GetMapping("/names")
    public ResponseEntity<List<FileNameAndId>> getPdfFileNames(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String guid = jwtService.extractAndDecryptGuid(jwt);

        log.info("gathering file names for user [{}]", guid);

        List<PdfFile> pdfFiles = pdfFileService.getAllPDFFilesForUser(guid);

        if (pdfFiles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<FileNameAndId> fileNameAndIds = pdfFiles.stream().map(file -> new FileNameAndId(file.getId(), file.getName())).collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(fileNameAndIds);
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


