package com.thesis.filemanager.filetypes.pdf;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PdfFileService {

    private final PdfFileRepository pdfFileRepository;

    public PdfFileService(PdfFileRepository pdfFileRepository) {
        this.pdfFileRepository = pdfFileRepository;
    }

    public PdfFile savePdfFile(String userGuid, MultipartFile file) throws IOException {
        //todo  fix user guid -> from token
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        PdfFile pdfFile = new PdfFile();
        pdfFile.setName(fileName);
        pdfFile.setContent(file.getBytes());
        pdfFile.setUserGuid(userGuid);

        LocalDate localDate = LocalDate.now();
        pdfFile.setCreatedDate(localDate);
        pdfFile.setLastModifiedDate(localDate);
        pdfFile.setSize((int) file.getSize());

        return pdfFileRepository.save(pdfFile);
    }

    public PdfFile savePdfFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        PdfFile pdfFile = new PdfFile();
        pdfFile.setName(fileName);
        pdfFile.setContent(file.getBytes());
        pdfFile.setUserGuid("52540395-3085-4124-99ea-f3174d131511");

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

