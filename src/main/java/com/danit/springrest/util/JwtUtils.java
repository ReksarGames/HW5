package com.danit.springrest.util;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    private static Set<Roles> getRoles(Claims claims) {
        final List<Map<String, String>> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(r -> Roles.valueOf(r.get("roleName")))
                .collect(Collectors.toSet());
    }

    @RequiredArgsConstructor
    public static enum Roles implements GrantedAuthority {
        USER("USER"), ADMIN("ADMIN");
        private final String value;

        @Override
        public String getAuthority() {

            return value;
        }
    }
}

