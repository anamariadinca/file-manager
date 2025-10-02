package com.thesis.filemanager.filetypes.pictures.selfie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface SelfiePictureRepository extends MongoRepository<SelfiePicture, String> {
}
