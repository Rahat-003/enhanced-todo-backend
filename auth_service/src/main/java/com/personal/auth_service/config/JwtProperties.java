package com.personal.auth_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    private String secret = "defaultSecretKey";
    private long expiration = 86400000; // 1 day
    private long refreshTokenExpiration = 604800000; // 7 days
}