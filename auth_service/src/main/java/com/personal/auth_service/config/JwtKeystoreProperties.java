package com.personal.auth_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt.keystore")
public class JwtKeystoreProperties {
    private String path;
    private String password;
    private String keyAlias;
}
