package com.personal.auth_service.service;

import com.personal.auth_service.dto.AuthRequest;
import com.personal.auth_service.dto.AuthResponse;
import com.personal.auth_service.dto.RegisterRequest;
import com.personal.auth_service.exception.InvalidCredentialsException;
import com.personal.auth_service.exception.UserAlreadyExistsException;
import com.personal.auth_service.exception.UserNotFoundException;
import com.personal.auth_service.util.JwtUtil;
import com.personal.domain.AppUser;
import com.personal.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (appUserRepository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Email {} already exists", request.getEmail());
            throw new UserAlreadyExistsException("Email already exists");
        }

        AppUser user = new AppUser();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        appUserRepository.save(user);
        log.info("User {} registered successfully", user.getUserName());

        return generateAuthResponse(user.getUserName());
    }

    public AuthResponse login(AuthRequest request) {
        AppUser user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.warn("User not found with email: {}", request.getEmail());
                    return new UserNotFoundException("User not found");
                });
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("Invalid credentials for user: {}", request.getEmail());
            throw new InvalidCredentialsException("Invalid credentials");
        }

        log.info("User {} logged in successfully", user.getUserName());
        // Generate JWT token
        return generateAuthResponse(user.getUserName());
    }

    public AuthResponse refreshToken(String refreshToken) {
        String username = jwtUtil.extractUsername(refreshToken);
        AppUser user = appUserRepository.findByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found "));
        if (jwtUtil.isTokenValid(refreshToken, new CustomUserDetails(user))) {
            return generateAuthResponse(username);
        }
        throw new InvalidCredentialsException("Invalid refresh token");
    }

    private AuthResponse generateAuthResponse(String username) {
        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

