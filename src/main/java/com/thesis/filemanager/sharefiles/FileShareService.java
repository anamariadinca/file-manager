package com.thesis.filemanager.sharefiles;

import com.thesis.filemanager.filetypes.pdf.PdfFile;
import com.thesis.filemanager.filetypes.pdf.PdfFileService;
import com.thesis.filemanager.institution.PublicInstitution;
import com.thesis.filemanager.institution.PublicInstitutionRepository;
import com.thesis.filemanager.email.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileShareService {

    private final PdfFileService pdfFileService;
    private final PublicInstitutionRepository publicInstitutionRepository;
    private final EmailService emailService;

    Logger logger = LoggerFactory.getLogger(FileShareService.class);

    public FileShareService(PdfFileService pdfFileService, PublicInstitutionRepository publicInstitutionRepository, EmailService emailService) {
        this.pdfFileService = pdfFileService;
        this.publicInstitutionRepository = publicInstitutionRepository;
        this.emailService = emailService;
    }

    public void shareFileWithPublicInstitution(Long fileId, String institutionId, String username) {
        logger.info("Sharing file with institution: [{}] on behalf of [{}]", institutionId, username);
        PdfFile pdfFile = pdfFileService.getPdfFileById(fileId);
        PublicInstitution institution = publicInstitutionRepository.findByUniqueIdentification(institutionId);

        if (pdfFile != null && institution != null) {
            String subject = "Document " + pdfFile.getName() + " sent on behalf of " + username;
            String emailBody = "Please find the attached document: " + pdfFile.getName();

            emailService.sendEmailWithAttachment(institution.getEmailAddress(), subject, emailBody, pdfFile.getContent());
        }
    }
}

