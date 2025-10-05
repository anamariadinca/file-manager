package com.thesis.filemanager.filetypes.pictures;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;
    private final PictureMetadataRepository pictureMetadataRepository;

    public PictureService(PictureRepository pictureRepository, PictureMetadataRepository pictureMetadataRepository) {
        this.pictureRepository = pictureRepository;
        this.pictureMetadataRepository = pictureMetadataRepository;
    }

    public void savePicture(String userGuid, MultipartFile file, PictureType pictureType) throws IOException {
        createPicture(userGuid, file, pictureType);
    }

    public Picture getPictureById(String id) {
        return pictureRepository.findById(id).orElse(null);
    }

    public PictureMetadata getFileMetadataById(String id) {
        return pictureMetadataRepository.findById(id).orElse(null);
    }

    public void deletePicture(String id) {
        pictureMetadataRepository.deleteById(id);
        pictureRepository.deleteById(id);
    }

    public void saveRegistrationPictures(String guid, MultipartFile idCardPhoto, MultipartFile selfiePhoto) throws IOException {
        createPicture(guid, idCardPhoto, PictureType.ID);
        createPicture(guid, selfiePhoto, PictureType.SELFIE);
    }

    private void createPicture(String guid, MultipartFile idCardPhoto, PictureType pictureType) throws IOException {
        String pictureFileName = StringUtils.cleanPath(idCardPhoto.getOriginalFilename());
        LocalDate localDate = LocalDate.now();

        String pictureUUID = UUID.randomUUID().toString();
        PictureMetadata pictureMetadata = new PictureMetadata();
        pictureMetadata.setId(pictureUUID);
        pictureMetadata.setName(pictureFileName);
        pictureMetadata.setSize((int) idCardPhoto.getSize());
        pictureMetadata.setDateUploaded(localDate);
        pictureMetadata.setUserGuid(guid);
        pictureMetadata.setPictureType(pictureType);
        pictureMetadataRepository.save(pictureMetadata);

        Picture picture = new Picture();
        picture.setId(pictureUUID);
        picture.setContent(idCardPhoto.getBytes());
        picture.setMetadata(pictureMetadata);
        pictureRepository.save(picture);
    }

    public Picture getPictureByUserGuid(String guid) {
        List<PictureMetadata> profilePictures = pictureMetadataRepository.findByUserGuidAndPictureType(guid, PictureType.PROFILE);
        if (!profilePictures.isEmpty()) {
            return pictureRepository.findById(profilePictures.get(0).getId()).get();
        }

        List<PictureMetadata> selfiePictures = pictureMetadataRepository.findByUserGuidAndPictureType(guid, PictureType.SELFIE);
        PictureMetadata metadata = selfiePictures.get(0);
        return pictureRepository.findById(metadata.getId()).get();
    }
}
