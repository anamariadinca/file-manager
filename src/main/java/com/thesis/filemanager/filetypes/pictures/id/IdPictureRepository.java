package com.thesis.filemanager.filetypes.pictures.id;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface IdPictureRepository extends MongoRepository<IdPicture, String> {
}
