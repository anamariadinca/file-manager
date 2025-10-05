package com.thesis.filemanager.filetypes.pictures;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface PictureRepository extends MongoRepository<Picture, String> {
}
