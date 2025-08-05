package com.seeker.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.seeker.OwnExceptions.UnauthorizedException;
import com.seeker.models.UserEntity;

@Component
public class AuthenticatedUserProvider {

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserEntity user) {
            return user;
        }

        throw new UnauthorizedException("Authenticated principal is not a UserEntity");
    }
}
