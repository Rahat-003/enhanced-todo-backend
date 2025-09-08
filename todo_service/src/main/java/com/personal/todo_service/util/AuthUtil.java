package com.personal.todo_service.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class AuthUtil {

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
