package com.thesis.filemanager;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Service
public class SecurityUtils {


    @Value("${security.encryption.key}")
    private String key;

    @Value("${security.encryption.algorithm}")
    private String algorithm;

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    private SecretKey secretKey;
    private static final int IV_LENGTH = 16;
    private final Logger log = LoggerFactory.getLogger(SecurityUtils.class);

    @PostConstruct
    public void init() {
        // Ensure key is exactly 32 bytes
        byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 32);
        this.secretKey = new SecretKeySpec(keyBytes, "AES");

        // Log key details (not in production!)
        log.debug("Encryption key length: {} bytes", keyBytes.length);
        log.debug("Algorithm: {}", algorithm);
    }

    public String encrypt(String data) {
        try {
            // Generate random IV
            byte[] iv = new byte[IV_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Initialize cipher
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

            // Encrypt
            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Combine IV and encrypted part
            byte[] combined = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

            String result = Base64.getEncoder().encodeToString(combined);
            log.debug("Encrypted: {} -> {}", data, result);
            return result;

        } catch (Exception e) {
            log.error("Encryption failed for value: {}", data, e);
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public String decrypt(String encryptedData) {
        try {
            // Decode from Base64
            byte[] combined = Base64.getDecoder().decode(encryptedData);

            // Extract IV
            byte[] iv = new byte[IV_LENGTH];
            System.arraycopy(combined, 0, iv, 0, iv.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Extract encrypted part
            byte[] encryptedBytes = new byte[combined.length - IV_LENGTH];
            System.arraycopy(combined, IV_LENGTH, encryptedBytes, 0, encryptedBytes.length);

            // Initialize cipher
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            // Decrypt
            byte[] decrypted = cipher.doFinal(encryptedBytes);
            String result = new String(decrypted, StandardCharsets.UTF_8);

            log.debug("Decrypted: {} -> {}", encryptedData, result);
            return result;

        } catch (Exception e) {
            log.error("Decryption failed for value: {}", encryptedData, e);
            throw new RuntimeException("Decryption failed", e);
        }
    }

}
