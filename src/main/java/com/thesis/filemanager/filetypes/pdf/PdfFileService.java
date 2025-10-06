package com.thesis.filemanager.filetypes.pdf;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PdfFileService {

    private final PdfFileRepository pdfFileRepository;
    private final PdfFileMetadataRepository pdfFileMetadataRepository;

    public PdfFileService(PdfFileRepository pdfFileRepository, PdfFileMetadataRepository pdfFileMetadataRepository) {
        this.pdfFileRepository = pdfFileRepository;
        this.pdfFileMetadataRepository = pdfFileMetadataRepository;
    }

    public PdfFile savePdfFile(String userGuid, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileId = UUID.randomUUID().toString();

        PdfFileMetadata metadata = new PdfFileMetadata();
        metadata.setId(fileId);
        metadata.setName(fileName);
        metadata.setUserGuid(userGuid);
        metadata.setDocumentType(getRandomDocumentType());
        metadata.setFavorite(false);

        LocalDate localDate = LocalDate.now();
        metadata.setCreatedDate(localDate);
        metadata.setLastModifiedDate(localDate);
        metadata.setSize((int) file.getSize());
        pdfFileMetadataRepository.save(metadata);

        PdfFile pdfFile = new PdfFile();
        pdfFile.setId(fileId);
        pdfFile.setContent(file.getBytes());
        pdfFile.setPdfFileMetadata(metadata);
        PdfFile savedFile = pdfFileRepository.save(pdfFile);
        return savedFile;
    }

    private static DocumentType getRandomDocumentType() {
        DocumentType[] documentTypes = DocumentType.values();
        Random random = new Random();
        DocumentType randomDocumentType = documentTypes[random.nextInt(documentTypes.length)];
        return randomDocumentType;
    }

    public PdfFile getPdfFileById(String id) {
        return pdfFileRepository.findById(id).orElse(null);
    }

    public String getPdfFileNameById(String id) {
        return pdfFileMetadataRepository.findById(id).get().getName();
    }

    public PdfFileMetadata getFileMetadataById(String id) {
        return pdfFileMetadataRepository.findById(id).orElse(null);
    }

    public void deletePdfFile(String id) {
        pdfFileRepository.deleteById(id);
        pdfFileMetadataRepository.deleteById(id);
    }

    public List<PdfFileMetadata> getAllPDFFilesForUser(String guid) {
        return pdfFileMetadataRepository.findByUserGuid(guid);
    }

    public List<PdfFileMetadata> getFavoritePDFFilesForUser(String guid) {
        return pdfFileMetadataRepository.findByUserGuidAndFavorite(guid, true);
    }

    @Transactional
    public void setFileAsFavorite(String guid, String fileId) {
        long noOfFavoriteFiles = pdfFileMetadataRepository.findByUserGuid(guid).stream().filter(PdfFileMetadata::isFavorite).count();

        if (noOfFavoriteFiles > 5) {
            throw new ArithmeticException();
        }

        Optional<PdfFileMetadata> fileMetadata = pdfFileMetadataRepository.findById(fileId);
        fileMetadata.ifPresent(pdfFileMetadata -> pdfFileMetadata.setFavorite(true));
    }
}

