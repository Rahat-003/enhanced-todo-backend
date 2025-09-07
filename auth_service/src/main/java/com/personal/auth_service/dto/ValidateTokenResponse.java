package com.personal.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateTokenResponse {
    private boolean valid;
    private Long id;
    private String email;
    private String role;
    private String userName;
}
