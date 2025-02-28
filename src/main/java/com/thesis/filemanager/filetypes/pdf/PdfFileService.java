package com.thesis.filemanager.filetypes.pdf;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

        return pdfFileRepository.save(pdfFile);
    }

    public PdfFile getPdfFileById(Long id) {
        return pdfFileRepository.findById(id).orElse(null);
    }

    public void deletePdfFile(Long id) {
        pdfFileRepository.deleteById(id);
    }
}

