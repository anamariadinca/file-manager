package com.thesis.filemanager;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/share")
public class FileShareController {

    @Autowired
    private FileShareService fileShareService;

    @PostMapping("/{fileId}/{institutionId}")
    public ResponseEntity<String> shareFileWithPublicInstitution(
            @PathVariable Long fileId,
            @PathVariable String institutionId,
            HttpSession session) {
        String username;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
            fileShareService.shareFileWithPublicInstitution(fileId, institutionId, username);
            return ResponseEntity.ok("File shared successfully");
        } else {
            return ResponseEntity.badRequest().body("User not authenticated");
        }
    }
}

