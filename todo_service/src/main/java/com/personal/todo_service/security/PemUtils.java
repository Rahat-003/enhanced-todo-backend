package com.personal.todo_service.security;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PemUtils {

    public static PublicKey parseRSAPublicKeyFromPem(String pem) throws Exception {
        // Remove header/footer and all line breaks
        String publicKeyPEM = pem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        // Decode Base64 to bytes
        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);

        // Build PublicKey from decoded bytes
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
}
