package com.thesis.filemanager.filetypes.selfie;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SelfiePictureRepository extends MongoRepository<SelfiePicture, String> {
}
