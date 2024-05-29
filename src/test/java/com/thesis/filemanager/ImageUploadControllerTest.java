package com.thesis.filemanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ImageUploadControllerTest {

    @Autowired
    private ImageController imageController;

    @Test
    public void shouldAcceptJpgFile() throws Exception {
        MockMultipartFile jpgFile = new MockMultipartFile(
                "file", "test.jpg", "image/jpeg", "Test Image Content".getBytes()
        );

        ResponseEntity<String> responseEntity = imageController.uploadImageFile(jpgFile);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void shouldAcceptPngFile() throws Exception {
        MockMultipartFile pngFile = new MockMultipartFile(
                "file", "test.png", "image/png", "Test Image Content".getBytes()
        );

        ResponseEntity<String> responseEntity = imageController.uploadImageFile(pngFile);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void shouldRejectOtherFileFormats() throws Exception {
        MockMultipartFile gifFile = new MockMultipartFile(
                "file", "test.gif", "image/gif", "Test Image Content".getBytes()
        );

        ResponseEntity<String> responseEntity = imageController.uploadImageFile(gifFile);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }
}

