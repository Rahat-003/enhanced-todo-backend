package com.personal.todo_service.util;

import com.personal.domain.AppUser;
import com.personal.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
//@RequiredArgsConstructor
public class AuthUtil {
//    private final AppUserRepository appUserRepository;
    private static AppUserRepository appUserRepository;

    @Autowired
    public AuthUtil(AppUserRepository appUserRepository) {
        AuthUtil.appUserRepository = appUserRepository;
    }

    /**
     * Get the current authenticated user's ID (from JWT 'sub' claim)
     */
    public static Long getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) return null;

        // In our filter, principal is userId (String)
        String userIdStr = auth.getPrincipal().toString();
        return Long.valueOf(userIdStr);
    }

    public static AppUser getUser() {
        Long userId = getUserId();
        if (userId == null || userId == 0) {
            throw new RuntimeException("User not found");
        }
        return appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    /**
     * Get the current authenticated user's roles as a list of strings
     */
    public static List<String> getRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return List.of();

        return auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
