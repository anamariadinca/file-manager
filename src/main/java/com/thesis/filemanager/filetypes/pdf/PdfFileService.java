package com.thesis.filemanager.filetypes.pdf;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PdfFileService {

    private final PdfFileRepository pdfFileRepository;

    public PdfFileService(PdfFileRepository pdfFileRepository) {
        this.pdfFileRepository = pdfFileRepository;
    }

    public PdfFile savePdfFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        PdfFile pdfFile = new PdfFile();
        pdfFile.setName(fileName);
        pdfFile.setContent(file.getBytes());
        pdfFile.setUserGuid("b1bde36c-670b-4712-b583-fc18525754fc");

        return pdfFileRepository.save(pdfFile);
    }

    public PdfFile getPdfFileById(Long id) {
        return pdfFileRepository.findById(id).orElse(null);
    }

    public void deletePdfFile(Long id) {
        pdfFileRepository.deleteById(id);
    }

    public List<PdfFile> getAllPDFFilesForUser(String guid) {
        return pdfFileRepository.findByUserGuid(guid);
    }
}

