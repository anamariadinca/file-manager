package com.thesis.filemanager.documents;

import java.time.LocalDate;

public record FileMetadata(String id, String fileName, LocalDate date, int size, boolean isFavorite) { }
