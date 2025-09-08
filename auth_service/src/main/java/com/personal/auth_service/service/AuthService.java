package com.personal.auth_service.service;

import com.personal.auth_service.dto.AuthRequest;
import com.personal.auth_service.dto.AuthResponse;
import com.personal.auth_service.dto.RegisterRequest;
import com.personal.auth_service.util.JwtUtil;
import com.personal.domain.AppUser;
import com.personal.domain.RefreshToken;
import com.personal.enumeration.Role;
import com.personal.repository.AppUserRepository;
import com.personal.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthResponse registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("User already exists");

        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .userName(request.getUserName()) // can still store for display
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .enabled(true)
                .locked(false)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        userRepository.save(user);

        CustomUserDetails userDetails = new CustomUserDetails(user);
        String accessToken = jwtUtil.generateAccessToken(userDetails);

//        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
//        saveRefreshToken(user, refreshToken);

        return AuthResponse.builder()
                .accessToken(accessToken)
//                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        CustomUserDetails userDetails = new CustomUserDetails(
                userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new BadCredentialsException("Invalid credentials"))
        );

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String accessToken = jwtUtil.generateAccessToken(userDetails);
//        String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

//        saveRefreshToken(userDetails.getAppUser(), refreshToken);

        return AuthResponse.builder()
                .accessToken(accessToken)
//                .refreshToken(refreshToken)
                .build();
    }

    private void saveRefreshToken(AppUser user, String refreshToken) {
        RefreshToken rt = RefreshToken.builder()
                .user(user)
                .token(refreshToken)
                .expiresAt(Instant.now().plusSeconds(604800))
                .revoked(false)
                .createdAt(Instant.now())
                .build();
        refreshTokenRepository.save(rt);
    }
}
