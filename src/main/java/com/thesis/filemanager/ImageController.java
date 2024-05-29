package com.thesis.filemanager;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private static final String[] ACCEPTED_FILE_TYPES = {"image/jpeg", "image/jpg", "image/png"};
    private static final String UPLOAD_DIRECTORY = "cd ~/Documents/doc/code/file-manager/images"; // Placeholder: Update this path

    private boolean isAcceptedFileType(String contentType) {
        for (String type : ACCEPTED_FILE_TYPES) {
            if (type.equalsIgnoreCase(contentType)) {
                return true;
            }
        }
        return false;
    }

    @GetMapping
    public ResponseEntity<List<Resource>> getAllImageFiles() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImageFile(@RequestParam("file") MultipartFile file) {
        if (!isAcceptedFileType(file.getContentType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only JPEG, JPG, and PNG files are allowed.");
        }

        // Define user-specific directory
        String userDirectory = UPLOAD_DIRECTORY;
        File userDir = new File(userDirectory);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        // Save the file with a unique identifier to prevent name clashes
        String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File targetFile = new File(userDir, uniqueFileName);

        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteImageFile(@RequestParam("userId") String userId, @RequestParam("fileName") String fileName) {
        Path filePath = Paths.get(UPLOAD_DIRECTORY, userId, fileName);

        if (Files.exists(filePath)) {
            try {
                Files.delete(filePath);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File deletion failed.");
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found.");
        }
    }

    @GetMapping("/{userId}/{fileName}")
    public ResponseEntity<Resource> getImageFile(@PathVariable String userId, @PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIRECTORY, userId, fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

