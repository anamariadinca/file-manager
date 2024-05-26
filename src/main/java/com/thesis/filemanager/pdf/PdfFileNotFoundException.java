package com.thesis.filemanager.pdf;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PdfFileNotFoundException extends RuntimeException {
    public PdfFileNotFoundException(String message) {
        super(message);
    }
}