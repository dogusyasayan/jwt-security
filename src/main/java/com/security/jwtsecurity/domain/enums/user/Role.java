package com.security.jwtsecurity.domain.enums.user;

import com.security.jwtsecurity.domain.user.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Enum representing different roles in the system.
 * Sistemdeki farklı rolleri temsil eden enum.
 */
@Getter
@RequiredArgsConstructor
public enum Role {

    /**
     * User role with no special permissions.
     * Özel bir izni olmayan kullanıcı rolü.
     */
    USER(Collections.emptySet(), "ROLE_USER"),

    /**
     * Admin role with various permissions.
     * Çeşitli izinlere sahip admin rolü.
     */
    ADMIN(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE,
                    Permission.ADMIN_CREATE,
                    Permission.MANAGER_READ,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_DELETE,
                    Permission.MANAGER_CREATE
            ),
            "ROLE_ADMIN"
    ),

    /**
     * Manager role with specific permissions.
     * Belirli izinlere sahip yönetici rolü.
     */
    MANAGER(
            Set.of(
                    Permission.MANAGER_READ,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_DELETE,
                    Permission.MANAGER_CREATE
            ),
            "ROLE_MANAGER"
    );

    private final Set<Permission> permissions;
    private final String roleName;

    /**
     * Get authorities (permissions) associated with the role.
     * Role ile ilişkilendirilmiş yetkileri (izinler) alır.
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority(roleName));
        return authorities;
    }
}
