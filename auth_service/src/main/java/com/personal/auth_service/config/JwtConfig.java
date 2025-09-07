package com.personal.auth_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {

    private final JwtKeystoreProperties jwtKeystoreProperties;

    @Bean
    public KeyStore keyStore() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream resourceAsStream = new ClassPathResource(jwtKeystoreProperties.getPath().replace("classpath:", "")).getInputStream();
            keyStore.load(resourceAsStream, jwtKeystoreProperties.getPassword().toCharArray());
            return keyStore;
        } catch (Exception e) {
            throw new RuntimeException("Unable to load keystore", e);
        }
    }

    @Bean
    public PrivateKey privateKey(KeyStore keyStore) {
        try {
            return (PrivateKey) keyStore.getKey(jwtKeystoreProperties.getKeyAlias(), jwtKeystoreProperties.getPassword().toCharArray());
        } catch (Exception e) {
            throw new RuntimeException("Unable to load private key", e);
        }
    }

    @Bean
    public PublicKey publicKey(KeyStore keyStore) {
        try {
            return keyStore.getCertificate(jwtKeystoreProperties.getKeyAlias()).getPublicKey();
        } catch (Exception e) {
            throw new RuntimeException("Unable to load public key", e);
        }
    }
}
