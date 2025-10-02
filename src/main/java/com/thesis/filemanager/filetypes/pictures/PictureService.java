package com.thesis.filemanager.filetypes.pictures;

import com.thesis.filemanager.filetypes.pdf.PdfFile;
import com.thesis.filemanager.filetypes.pdf.PdfFileMetadata;
import com.thesis.filemanager.filetypes.pictures.id.IdPicture;
import com.thesis.filemanager.filetypes.pictures.id.IdPictureMetadata;
import com.thesis.filemanager.filetypes.pictures.id.IdPictureMetadataRepository;
import com.thesis.filemanager.filetypes.pictures.id.IdPictureRepository;
import com.thesis.filemanager.filetypes.pictures.selfie.SelfiePicture;
import com.thesis.filemanager.filetypes.pictures.selfie.SelfiePictureMetadata;
import com.thesis.filemanager.filetypes.pictures.selfie.SelfiePictureMetadataRepository;
import com.thesis.filemanager.filetypes.pictures.selfie.SelfiePictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PictureService {

    private final IdPictureRepository idPictureRepository;
    private final SelfiePictureRepository selfiePictureRepository;
    private final IdPictureMetadataRepository idPictureMetadataRepository;
    private final SelfiePictureMetadataRepository selfiePictureMetadataRepository;

    public PictureService(IdPictureRepository idPictureRepository, SelfiePictureRepository selfiePictureRepository, IdPictureMetadataRepository idPictureMetadataRepository, SelfiePictureMetadataRepository selfiePictureMetadataRepository) {
        this.idPictureRepository = idPictureRepository;
        this.selfiePictureRepository = selfiePictureRepository;
        this.idPictureMetadataRepository = idPictureMetadataRepository;
        this.selfiePictureMetadataRepository = selfiePictureMetadataRepository;
    }

    public void saveIdPicture(String userGuid, MultipartFile file) throws IOException {

    }


    public IdPicture getPictureById(String id) {
        return idPictureRepository.findById(id).orElse(null);
    }

    public IdPictureMetadata getFileMetadataById(String id) {
        return idPictureMetadataRepository.findById(id).orElse(null);
    }

    public void deletePdfFile(String id) {

    }

    public List<PdfFileMetadata> getAllPDFFilesForUser(String guid) {
        return null;
    }

    public String saveRegistrationPictures(String guid, MultipartFile idCardPhoto, MultipartFile selfiePhoto) throws IOException {
        String idCardPictureFileName = StringUtils.cleanPath(idCardPhoto.getOriginalFilename());
        String selfiePictureFileName = StringUtils.cleanPath(selfiePhoto.getOriginalFilename());
        String picturesId = UUID.randomUUID().toString();
        LocalDate localDate = LocalDate.now();

        IdPictureMetadata idPictureMetadata = new IdPictureMetadata();
        idPictureMetadata.setId(picturesId);
        idPictureMetadata.setName(idCardPictureFileName);
        idPictureMetadata.setSize((int) idCardPhoto.getSize());
        idPictureMetadata.setDateUploaded(localDate);
        idPictureMetadata.setUserGuid(guid);
        idPictureMetadataRepository.save(idPictureMetadata);

        IdPicture idPicture = new IdPicture();
        idPicture.setId(picturesId);
        idPicture.setContent(idCardPhoto.getBytes());
        idPicture.setMetadata(idPictureMetadata);
        idPictureRepository.save(idPicture);

        SelfiePictureMetadata selfiePictureMetadata = new SelfiePictureMetadata();
        selfiePictureMetadata.setId(picturesId);
        selfiePictureMetadata.setName(selfiePictureFileName);
        selfiePictureMetadata.setSize((int) selfiePhoto.getSize());
        selfiePictureMetadata.setDateUploaded(localDate);
        selfiePictureMetadata.setUserGuid(guid);
        selfiePictureMetadataRepository.save(selfiePictureMetadata);

        SelfiePicture selfiePicture = new SelfiePicture();
        selfiePicture.setId(picturesId);
        selfiePicture.setContent(idCardPhoto.getBytes());
        selfiePicture.setMetadata(selfiePictureMetadata);
        selfiePictureRepository.save(selfiePicture);

        return picturesId;
    }
}

