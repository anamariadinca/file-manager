package com.thesis.filemanager;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pdfFiles")
public class PdfFileController {

    @Autowired
    private PdfFileService pdfFileService;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<PdfFile> getAllPdfFiles() {
        return pdfFileService.getAllPdfFiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> getPdfFileById(@PathVariable Long id) {
        PdfFile pdfFile = pdfFileService.getPdfFileById(id);
        ByteArrayResource resource = new ByteArrayResource(pdfFile.getContent());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfFile.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

@PostMapping
public Long savePdfFile(@RequestParam("file") MultipartFile file) {
    return pdfFileService.savePdfFile(file).getId();
}

    @DeleteMapping("/{id}")
    public void deletePdfFile(@PathVariable Long id) {
        pdfFileService.deletePdfFile(id);
    }

    @PostMapping("/send/{fileId}/to/{institutionId}")
    public void sendPdfFile(@PathVariable Long fileId, @PathVariable Long institutionId, HttpServletRequest request) throws MessagingException, MessagingException {
        PdfFile pdfFile = pdfFileService.getPdfFileById(fileId);
        Institution institution = institutionRepository.findById(institutionId).orElseThrow(() -> new NoSuchElementException("Institution not found with id " + institutionId));

        Principal principal = request.getUserPrincipal();
        String subject = "Document " + pdfFile.getFilename() + " sent on behalf of " + principal.getName();

        emailService.sendEmailWithAttachment(institution.getEmail(), subject, pdfFile.getContent(), pdfFile.getFilename());
    }

}