package com.thesis.filemanager;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {

    @GetMapping("/csrf")
    public String getDeferredCsrfToken(CsrfToken deferredCsrfToken) {
        return deferredCsrfToken.getToken();
    }
}
