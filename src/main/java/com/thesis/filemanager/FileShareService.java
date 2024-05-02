package com.thesis.filemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileShareService {

    @Autowired
    private PdfFileService pdfFileService;

    @Autowired
    private PublicInstitutionRepository publicInstitutionRepository;

    @Autowired
    private EmailService emailService;

    public void shareFileWithPublicInstitution(Long fileId, String institutionId, String username) {
        PdfFile pdfFile = pdfFileService.getPdfFileById(fileId);
        PublicInstitution institution = publicInstitutionRepository.findByUniqueIdentification(institutionId);

        if (pdfFile != null && institution != null) {
            String subject = "Document " + pdfFile.getName() + " sent on behalf of " + username;
            String emailBody = "Please find the attached document: " + pdfFile.getName();

            emailService.sendEmailWithAttachment(institution.getEmailAddress(), subject, emailBody, pdfFile.getContent());
        }
    }
}

