package com.thesis.filemanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ImageController.class})
@ExtendWith(SpringExtension.class)
class ImageControllerDiffblueTest {
    @Autowired
    private ImageController imageController;

    /**
     * Method under test: {@link ImageController#getAllImageFiles()}
     */
    @Test
    void testGetAllImageFiles() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(501));
    }

    /**
     * Method under test: {@link ImageController#getAllImageFiles()}
     */
    @Test
    void testGetAllImageFiles2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images", "Uri Variables");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(501));
    }

    /**
     * Method under test: {@link ImageController#getAllImageFiles()}
     */
    @Test
    void testGetAllImageFiles3() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images");
        requestBuilder.contentType("text/plain");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(501));
    }

    /**
     * Method under test: {@link ImageController#getAllImageFiles()}
     */
    @Test
    void testGetAllImageFiles4() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images");
        requestBuilder.accept("https://example.org/example");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(501));
    }

    /**
     * Method under test: {@link ImageController#getAllImageFiles()}
     */
    @Test
    void testGetAllImageFiles5() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images");
        requestBuilder.accept("");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(501));
    }

    /**
     * Method under test: {@link ImageController#getAllImageFiles()}
     */
    @Test
    void testGetAllImageFiles6() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images");
        requestBuilder.content("AXAXAXAX".getBytes("UTF-8"));
        requestBuilder.contentType("text/plain");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(501));
    }

    /**
     * Method under test: {@link ImageController#getImageFile(String, String)}
     */
    @Test
    void testGetImageFile() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images/{userId}/{fileName}", "42",
                "foo.txt");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ImageController#getImageFile(String, String)}
     */
    @Test
    void testGetImageFile2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images/{userId}/{fileName}",
                "Uri Variables", "Uri Variables", "Uri Variables");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ImageController#getImageFile(String, String)}
     */
    @Test
    void testGetImageFile3() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images/{userId}/{fileName}", "42",
                "foo.txt");
        requestBuilder.contentType("text/plain");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ImageController#getImageFile(String, String)}
     */
    @Test
    void testGetImageFile4() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images/{userId}/{fileName}", "42",
                "foo.txt");
        requestBuilder.accept("https://example.org/example");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ImageController#getImageFile(String, String)}
     */
    @Test
    void testGetImageFile5() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images/{userId}/{fileName}", "42",
                "foo.txt");
        requestBuilder.accept("");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ImageController#getImageFile(String, String)}
     */
    @Test
    void testGetImageFile6() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/images/{userId}/{fileName}", "42",
                "foo.txt");
        requestBuilder.content(new byte[]{'A', 2, 'A', 2, 'A', 2, 'A', 2});
        requestBuilder.contentType("text/plain");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
