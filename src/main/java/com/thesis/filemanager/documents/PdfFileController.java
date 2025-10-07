package com.thesis.filemanager.documents;

import com.thesis.filemanager.config.JwtService;
import com.thesis.filemanager.filetypes.pdf.PdfFile;
import com.thesis.filemanager.filetypes.pdf.PdfFileMetadata;
import com.thesis.filemanager.filetypes.pdf.PdfFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

        List<PdfFileMetadata> filesMetadata = pdfFileService.getAllPDFFilesForUser(guid);

        if (filesMetadata.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<FileNameAndId> fileNameAndIds = filesMetadata.stream().map(file -> new FileNameAndId(file.getId(), file.getName())).collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(fileNameAndIds);
    }

    @GetMapping("{id}/name")
    public ResponseEntity<Map<String, String>> getPdfFileNameById(@PathVariable String id) {

        log.info("getPdfFileNameById for doc id [{}]", id);

        String fileName = pdfFileService.getPdfFileNameById(id);
        Map<String, String> responseBody = Collections.singletonMap("fileName", fileName);

        return ResponseEntity.ok()
                .body(responseBody);
    }

    @GetMapping("/metadata")
    public ResponseEntity<List<FileMetadata>> getPdfFilesMetadata(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String guid = jwtService.extractAndDecryptGuid(jwt);

        log.info("gathering file names for user [{}]", guid);

        List<PdfFileMetadata> filesMetadata = pdfFileService.getAllPDFFilesForUser(guid);

        if (filesMetadata.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<FileMetadata> fileMetadata = filesMetadata.stream().map(file -> new FileMetadata(file.getId(), file.getName(), file.getCreatedDate(), file.getSize(), file.isFavorite())).collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(fileMetadata);
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<FileMetadata>> getFavoritePdfFilesMetadata(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String guid = jwtService.extractAndDecryptGuid(jwt);

        log.info("gathering file names for user [{}]", guid);

        List<PdfFileMetadata> filesMetadata = pdfFileService.getFavoritePDFFilesForUser(guid);

        if (filesMetadata.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<FileMetadata> fileMetadata = filesMetadata.stream().map(file -> new FileMetadata(file.getId(), file.getName(), file.getCreatedDate(), file.getSize(), file.isFavorite())).collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(fileMetadata);
    }

    @PostMapping
    public ResponseEntity<String> uploadPdfFile(@RequestHeader("Authorization") String token, @RequestParam("file") MultipartFile file) {
        String jwt = token.substring(7);
        String guid = jwtService.extractAndDecryptGuid(jwt);

        log.info("gathering file names for user [{}]", guid);

        try {
            PdfFile pdfFile = pdfFileService.savePdfFile(guid, file);
            return ResponseEntity.ok("File uploaded successfully with ID: " + pdfFile.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @PostMapping("/{fileId}/favorite")
    public ResponseEntity<String> setFavoriteFile(@RequestHeader("Authorization") String token, @PathVariable String fileId) {
        String jwt = token.substring(7);
        String guid = jwtService.extractAndDecryptGuid(jwt);

        log.info("gathering file names for user [{}]", guid);

        try {
            pdfFileService.setFileAsFavorite(guid, fileId);
            return ResponseEntity.ok("File set as favorite successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getPdfFile(@PathVariable String id) {
        PdfFile pdfFile = pdfFileService.getPdfFileById(id);
        if (pdfFile == null) {
            return ResponseEntity.ok().build();
        }

        PdfFileMetadata fileMetadata = pdfFileService.getFileMetadataById(id);
        if (fileMetadata == null) {
            return ResponseEntity.ok().build();
        }

        byte[] pdfContent = pdfFile.getContent();
        Resource resource = new ByteArrayResource(pdfContent);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                .filename(fileMetadata.getName())
                .build();
        headers.setContentDisposition(contentDisposition);
        headers.setContentLength(pdfContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePdfFile(@PathVariable String id) {
        PdfFile pdfFile = pdfFileService.getPdfFileById(id);
        if (pdfFile == null) {
            return ResponseEntity.ok().build();
        }

        pdfFileService.deletePdfFile(id);
        return ResponseEntity.ok("File deleted successfully");
    }
}


