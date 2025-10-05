package com.thesis.filemanager.pictures;

import com.thesis.filemanager.config.JwtService;
import com.thesis.filemanager.filetypes.pictures.Picture;
import com.thesis.filemanager.filetypes.pictures.PictureMetadata;
import com.thesis.filemanager.filetypes.pictures.PictureService;
import com.thesis.filemanager.filetypes.pictures.PictureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/pictures")
public class PicturesController {

    private final PictureService pictureService;
    private final JwtService jwtService;
    private final Logger log = LoggerFactory.getLogger(PicturesController.class);

    public PicturesController(PictureService pictureService, JwtService jwtService) {
        this.pictureService = pictureService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<String> uploadPicture(@RequestHeader("Authorization") String token,
                                                @RequestParam("picture") MultipartFile picture,
                                                @RequestParam("pictureType") PictureType pictureType) {
        String jwt = token.substring(7);
        String guid = jwtService.extractAndDecryptGuid(jwt);

        log.info("gathering file names for user [{}]", guid);
        try {
            pictureService.savePicture(guid, picture, pictureType);
            return ResponseEntity.ok("Pictures uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @PostMapping("/registrationPics")
    public ResponseEntity<String> uploadRegistrationPictures(@RequestHeader("Authorization") String token,
                                                             @RequestParam("idCardPhoto") MultipartFile idCardPhoto,
                                                             @RequestParam("selfiePhoto") MultipartFile selfiePhoto) {
        String jwt = token.substring(7);
        String guid = jwtService.extractAndDecryptGuid(jwt);

        log.info("gathering file names for user [{}]", guid);

        try {
            pictureService.saveRegistrationPictures(guid, idCardPhoto, selfiePhoto);
            return ResponseEntity.ok("Pictures uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getPicture(@PathVariable String id) {
        Picture idPicture = pictureService.getPictureById(id);
        if (idPicture == null) {
            return ResponseEntity.ok().build();
        }

        PictureMetadata fileMetadata = pictureService.getFileMetadataById(id);
        if (fileMetadata == null) {
            return ResponseEntity.ok().build();
        }

        byte[] pdfContent = idPicture.getContent();
        Resource resource = new ByteArrayResource(pdfContent);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileMetadata.getName());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    @GetMapping("/getProfilePic")
    public ResponseEntity<Resource> getProfilePicture(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String guid = jwtService.extractAndDecryptGuid(jwt);

        log.info("gathering file names for user [{}]", guid);


        Picture idPicture = pictureService.getPictureByUserGuid(guid);
        if (idPicture == null) {
            return ResponseEntity.ok().build();
        }

        byte[] pdfContent = idPicture.getContent();
        Resource resource = new ByteArrayResource(pdfContent);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + guid + "_profile_pic");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    @GetMapping("/{guid}/getProfilePic")
    public ResponseEntity<Resource> getProfilePictureByUserGuid(@PathVariable String guid) {
        log.info("gathering file names for user [{}]", guid);


        Picture idPicture = pictureService.getPictureByUserGuid(guid);
        if (idPicture == null) {
            return ResponseEntity.ok().build();
        }

        byte[] pictureContent = idPicture.getContent();
        Resource resource = new ByteArrayResource(pictureContent);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + guid + "_profile_pic.jpeg");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}


