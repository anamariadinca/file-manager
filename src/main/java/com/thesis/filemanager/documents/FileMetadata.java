package com.thesis.filemanager.documents;

import com.thesis.filemanager.filetypes.pdf.DocumentType;

import java.time.LocalDate;

public record FileMetadata(String id, String fileName, String documentType, LocalDate date, int size, boolean isFavorite) { }
