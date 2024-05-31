package com.thesis.filemanager;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import org.junit.jupiter.api.Disabled;
import java.util.List;
import org.mockito.stubbing.Answer;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.mockito.MockedStatic;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class ImageControllerSapientGeneratedTest {

    private final MultipartFile fileMock = mock(MultipartFile.class);

    private final MediaType mediaTypeMock = mock(MediaType.class);

    private final Path pathMock = mock(Path.class);

    private final ResponseEntity.BodyBuilder responseEntityBodyBuilderMock = mock(ResponseEntity.BodyBuilder.class);

    private final ResponseEntity.BodyBuilder responseEntityBodyBuilderMock2 = mock(ResponseEntity.BodyBuilder.class);

    private final ResponseEntity.BodyBuilder responseEntityBodyBuilderMock3 = mock(ResponseEntity.BodyBuilder.class);

    private final ResponseEntity<Resource> responseEntityMock = mock(ResponseEntity.class);

    private final ResponseEntity<Resource> responseEntityMock2 = mock(ResponseEntity.class);

    //Sapient generated method id: ${getAllImageFilesTest}, hash: D5226FB5398B47ECEBE523F5832B28FE
    @Test()
    void getAllImageFilesTest() {
        //Arrange Statement(s)
        ResponseEntity<List<Resource>> responseEntityMock = mock(ResponseEntity.class);
        try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class)) {
            responseEntity.when(() -> ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)).thenReturn(responseEntityBodyBuilderMock);
            doReturn(responseEntityMock).when(responseEntityBodyBuilderMock).body(null);
            ImageController target = new ImageController();
            //Act Statement(s)
            ResponseEntity<List<Resource>> result = target.getAllImageFiles();
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntityMock));
                responseEntity.verify(() -> ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED), atLeast(1));
                verify(responseEntityBodyBuilderMock).body(null);
            });
        }
    }

    //Sapient generated method id: ${uploadImageFileWhenIsAcceptedFileTypeNotFileGetContentType}, hash: BA95CD7BCACB0FBF5A238E2F365C95D9
    @Test()
    void uploadImageFileWhenIsAcceptedFileTypeNotFileGetContentType() {
        /* Branches:
         * (for-each(ACCEPTED_FILE_TYPES)) : true  #  inside isAcceptedFileType method
         * (type.equalsIgnoreCase(contentType)) : false  #  inside isAcceptedFileType method
         * (!isAcceptedFileType(file.getContentType())) : true
         */
        //Arrange Statement(s)
        doReturn("A").when(fileMock).getContentType();
        ImageController target = new ImageController();
        //Act Statement(s)
        ResponseEntity<String> result = target.uploadImageFile(fileMock);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
        ResponseEntity<String> responseEntity = bodyBuilder.body("Only JPEG, JPG, and PNG files are allowed.");
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(responseEntity));
            verify(fileMock).getContentType();
        });
    }

    //Sapient generated method id: ${uploadImageFileWhenUserDirNotExists}, hash: 707BA41344213C162AD9204E57967D84
    @Test()
    void uploadImageFileWhenUserDirNotExists() throws IOException, IllegalStateException {
        /* Branches:
         * (for-each(ACCEPTED_FILE_TYPES)) : true  #  inside isAcceptedFileType method
         * (type.equalsIgnoreCase(contentType)) : true  #  inside isAcceptedFileType method
         * (!isAcceptedFileType(file.getContentType())) : false
         * (!userDir.exists()) : true
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        try (MockedStatic<UUID> uUID = mockStatic(UUID.class)) {
            doReturn("IMAGE/JPEG").when(fileMock).getContentType();
            doReturn("B").when(fileMock).getOriginalFilename();
            doNothing().when(fileMock).transferTo((File) any());
            UUID uUID2 = UUID.fromString("12345678-0000-abcd-1234-abcdef123456");
            uUID.when(() -> UUID.randomUUID()).thenReturn(uUID2);
            ImageController target = new ImageController();
            //Act Statement(s)
            ResponseEntity<String> result = target.uploadImageFile(fileMock);
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.OK);
            ResponseEntity<String> responseEntity = bodyBuilder.build();
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntity));
                verify(fileMock).getContentType();
                verify(fileMock).getOriginalFilename();
                verify(fileMock).transferTo((File) any());
                uUID.verify(() -> UUID.randomUUID(), atLeast(1));
            });
        }
    }

    //Sapient generated method id: ${uploadImageFileWhenCaughtIOException}, hash: EF4CFA4F2428D5B371F8DCBC35602113
    @Disabled()
    @Test()
    void uploadImageFileWhenCaughtIOException() throws IOException, IllegalStateException {
        /* Branches:
         * (for-each(ACCEPTED_FILE_TYPES)) : true  #  inside isAcceptedFileType method
         * (type.equalsIgnoreCase(contentType)) : true  #  inside isAcceptedFileType method
         * (!isAcceptedFileType(file.getContentType())) : false
         * (!userDir.exists()) : true
         * (catch-exception (IOException)) : true
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        try (MockedStatic<UUID> uUID = mockStatic(UUID.class)) {
            doReturn("IMAGE/JPEG").when(fileMock).getContentType();
            doReturn("B").when(fileMock).getOriginalFilename();
            doNothing().when(fileMock).transferTo((File) any());
            UUID uUID2 = UUID.fromString("12345678-0000-abcd-1234-abcdef123456");
            uUID.when(() -> UUID.randomUUID()).thenReturn(uUID2);
            ImageController target = new ImageController();
            //Act Statement(s)
            ResponseEntity<String> result = target.uploadImageFile(fileMock);
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            ResponseEntity<String> responseEntity = bodyBuilder.body("File upload failed.");
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntity));
                verify(fileMock).getContentType();
                verify(fileMock).getOriginalFilename();
                verify(fileMock).transferTo((File) any());
                uUID.verify(() -> UUID.randomUUID(), atLeast(1));
            });
        }
    }

    //Sapient generated method id: ${deleteImageFileWhenFilesExistsFilePath}, hash: 80070E265D6B9EFB6FDC9E8628DAA81D
    @Disabled()
    @Test()
    void deleteImageFileWhenFilesExistsFilePath() {
        /* Branches:
         * (Files.exists(filePath)) : true
         *
         * TODO: Help needed! This method is not unit testable!
         *  Potential harmful system call (Files.delete) detected; Learn more: https://github.com/Sapient-AI/docs#disabled-generated-tests
         *  Suggestions:
         *  This method should be avoided from unit testing. This can be covered during integration testing.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        ImageController target = new ImageController();
        //Act Statement(s)
        ResponseEntity<String> result = target.deleteImageFile("userId1", "fileName1");
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.NO_CONTENT);
        ResponseEntity<String> responseEntity = bodyBuilder.build();
        //Assert statement(s)
        assertAll("result", () -> assertThat(result, equalTo(responseEntity)));
    }

    //Sapient generated method id: ${deleteImageFileWhenFilesNotExistsFilePath}, hash: 7AF28BBAB0306B99DB204DEE503B1663
    @Test()
    void deleteImageFileWhenFilesNotExistsFilePath() {
        /* Branches:
         * (Files.exists(filePath)) : false
         */
        //Arrange Statement(s)
        ImageController target = new ImageController();
        //Act Statement(s)
        ResponseEntity<String> result = target.deleteImageFile("userId1", "fileName1");
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.NOT_FOUND);
        ResponseEntity<String> responseEntity = bodyBuilder.body("File not found.");
        //Assert statement(s)
        assertAll("result", () -> assertThat(result, equalTo(responseEntity)));
    }

    //Sapient generated method id: ${deleteImageFileWhenCaughtIOException}, hash: 3F654EC978C2FC87028BDE6326C139A0
    @Disabled()
    @Test()
    void deleteImageFileWhenCaughtIOException() throws IOException {
        /* Branches:
         * (Files.exists(filePath)) : true
         * (catch-exception (IOException)) : true
         *
         * TODO: Help needed! This method is not unit testable!
         *  Potential harmful system call (Files.delete) detected; Learn more: https://github.com/Sapient-AI/docs#disabled-generated-tests
         *  Suggestions:
         *  This method should be avoided from unit testing. This can be covered during integration testing.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        try (MockedStatic<Files> files = mockStatic(Files.class)) {
            files.when(() -> Files.delete(pathMock)).thenAnswer((Answer<Void>) invocation -> null);
            ImageController target = new ImageController();
            //Act Statement(s)
            ResponseEntity<String> result = target.deleteImageFile("userId1", "fileName1");
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            ResponseEntity<String> responseEntity = bodyBuilder.body("File deletion failed.");
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntity));
                files.verify(() -> Files.delete(pathMock), atLeast(1));
            });
        }
    }

    //Sapient generated method id: ${getImageFileWhenResourceIsReadable}, hash: A78AA413E2BA54F1A6354D65CAE3443A
//    @Disabled()
    @Test()
    void getImageFileWhenResourceIsReadable() {
        /* Branches:
         * (resource.exists()) : true
         * (resource.isReadable()) : true
         */
        //Arrange Statement(s)
        try (MockedStatic<MediaType> mediaType = mockStatic(MediaType.class);
            MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class)) {

            responseEntity.when(ResponseEntity::ok).thenReturn(responseEntityBodyBuilderMock);
            doReturn(responseEntityBodyBuilderMock2).when(responseEntityBodyBuilderMock).contentType(mediaTypeMock);

            String[] stringArray = new String[] { "attachment; filename=\"A\"" };
            doReturn(responseEntityBodyBuilderMock3).when(responseEntityBodyBuilderMock2).header("Content-Disposition", stringArray);
            doReturn(responseEntityMock).when(responseEntityBodyBuilderMock3).body((UrlResource) any());
            mediaType.when(() -> MediaType.parseMediaType("mediaType1")).thenReturn(mediaTypeMock);

            ImageController target = new ImageController();
            target.setUploadDirectory("~/Documents/doc/code/file-manager/src/test/java/resources");
            //Act Statement(s)
            ResponseEntity<Resource> result = target.getImageFile("userId1", "file1.jpg");
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntityMock));
                responseEntity.verify(() -> ResponseEntity.ok(), atLeast(1));
                verify(responseEntityBodyBuilderMock).contentType(mediaTypeMock);
                verify(responseEntityBodyBuilderMock2).header("Content-Disposition", stringArray);
                verify(responseEntityBodyBuilderMock3).body((UrlResource) any());
                mediaType.verify(() -> MediaType.parseMediaType("mediaType1"), atLeast(1));
            });
        }
    }

    //Sapient generated method id: ${getImageFileWhenResourceNotIsReadable}, hash: B8FE5150A4D3E379DE32C1947A095914
    @Test()
    void getImageFileWhenResourceNotIsReadable() {
        /* Branches:
         * (resource.exists()) : true
         * (resource.isReadable()) : false
         *
         * TODO: Help needed! This method is not unit testable!
         *  A variable could not be isolated/mocked when calling a method - Variable name: resource - Method: exists
         *  Suggestions:
         *  You can pass them as constructor arguments or create a setter for them (avoid new operator)
         *  or adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class)) {
            responseEntity.when(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)).thenReturn(responseEntityBodyBuilderMock);
            doReturn(responseEntityMock).when(responseEntityBodyBuilderMock).body(null);
            ImageController target = new ImageController();
            //Act Statement(s)
            ResponseEntity<Resource> result = target.getImageFile("userId1", "fileName1");
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntityMock));
                responseEntity.verify(() -> ResponseEntity.status(HttpStatus.NOT_FOUND), atLeast(1));
                verify(responseEntityBodyBuilderMock).body(null);
            });
        }
    }

    //Sapient generated method id: ${getImageFileWhenResourceIsReadableAndCaughtMalformedURLException}, hash: F916BA05C435DBF38FB22FFBE1D85F11
    @Disabled()
    @Test()
    void getImageFileWhenResourceIsReadableAndCaughtMalformedURLException() throws IOException {
        /* Branches:
         * (resource.exists()) : true
         * (resource.isReadable()) : true
         * (catch-exception (MalformedURLException)) : true
         *
         * TODO: Help needed! This method is not unit testable!
         *  A variable could not be isolated/mocked when calling a method - Variable name: resource - Method: exists
         *  Suggestions:
         *  You can pass them as constructor arguments or create a setter for them (avoid new operator)
         *  or adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        ResponseEntity responseEntityMock = mock(ResponseEntity.class);
        ResponseEntity.BodyBuilder responseEntityBodyBuilderMock4 = mock(ResponseEntity.BodyBuilder.class);
        try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class);
            MockedStatic<MediaType> mediaType = mockStatic(MediaType.class);
            MockedStatic<Files> files = mockStatic(Files.class)) {
            files.when(() -> Files.probeContentType(pathMock)).thenReturn("return_of_probeContentType1");
            responseEntity.when(() -> ResponseEntity.ok()).thenReturn(responseEntityBodyBuilderMock);
            doReturn(responseEntityBodyBuilderMock2).when(responseEntityBodyBuilderMock).contentType(mediaTypeMock);
            String[] stringArray = new String[] { "attachment; filename=\"A\"" };
            doReturn(responseEntityBodyBuilderMock3).when(responseEntityBodyBuilderMock2).header("Content-Disposition", stringArray);
            doReturn(responseEntityMock).when(responseEntityBodyBuilderMock3).body((UrlResource) any());
            mediaType.when(() -> MediaType.parseMediaType("return_of_probeContentType1")).thenReturn(mediaTypeMock);
            responseEntity.when(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)).thenReturn(responseEntityBodyBuilderMock4);
            doReturn(responseEntityMock2).when(responseEntityBodyBuilderMock4).body(null);
            ImageController target = new ImageController();
            //Act Statement(s)
            ResponseEntity<Resource> result = target.getImageFile("userId1", "fileName1");
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntityMock2));
                files.verify(() -> Files.probeContentType(pathMock), atLeast(1));
                responseEntity.verify(() -> ResponseEntity.ok(), atLeast(1));
                verify(responseEntityBodyBuilderMock).contentType(mediaTypeMock);
                verify(responseEntityBodyBuilderMock2).header("Content-Disposition", stringArray);
                verify(responseEntityBodyBuilderMock3).body((UrlResource) any());
                mediaType.verify(() -> MediaType.parseMediaType("return_of_probeContentType1"), atLeast(1));
                responseEntity.verify(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR), atLeast(1));
                verify(responseEntityBodyBuilderMock4).body(null);
            });
        }
    }

    //Sapient generated method id: ${getImageFileWhenResourceNotIsReadableAndCaughtIOException}, hash: 8767F3F4FA2B69763247E369F51BD59E
    @Disabled()
    @Test()
    void getImageFileWhenResourceNotIsReadableAndCaughtIOException() {
        /* Branches:
         * (resource.exists()) : true
         * (resource.isReadable()) : false
         * (catch-exception (IOException)) : true
         *
         * TODO: Help needed! This method is not unit testable!
         *  A variable could not be isolated/mocked when calling a method - Variable name: resource - Method: exists
         *  Suggestions:
         *  You can pass them as constructor arguments or create a setter for them (avoid new operator)
         *  or adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        ResponseEntity responseEntityMock = mock(ResponseEntity.class);
        try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class)) {
            responseEntity.when(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)).thenReturn(responseEntityBodyBuilderMock);
            doReturn(responseEntityMock).when(responseEntityBodyBuilderMock).body(null);
            responseEntity.when(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)).thenReturn(responseEntityBodyBuilderMock2);
            doReturn(responseEntityMock2).when(responseEntityBodyBuilderMock2).body(null);
            ImageController target = new ImageController();
            //Act Statement(s)
            ResponseEntity<Resource> result = target.getImageFile("userId1", "fileName1");
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntityMock2));
                responseEntity.verify(() -> ResponseEntity.status(HttpStatus.NOT_FOUND), atLeast(1));
                verify(responseEntityBodyBuilderMock).body(null);
                responseEntity.verify(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR), atLeast(1));
                verify(responseEntityBodyBuilderMock2).body(null);
            });
        }
    }
}
