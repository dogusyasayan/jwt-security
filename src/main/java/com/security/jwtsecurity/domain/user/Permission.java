package com.security.jwtsecurity.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing different permissions in the system.
 * Sistemdeki farklı izinleri temsil eden enum.
 */
@RequiredArgsConstructor
public enum Permission {

    /**
     * Permission to read admin-related information.
     * Admin ile ilgili bilgileri okuma izni.
     */
    ADMIN_READ("admin:read"),

    /**
     * Permission to update admin-related information.
     * Admin ile ilgili bilgileri güncelleme izni.
     */
    ADMIN_UPDATE("admin:update"),

    /**
     * Permission to create admin-related information.
     * Admin ile ilgili bilgileri oluşturma izni.
     */
    ADMIN_CREATE("admin:create"),

    /**
     * Permission to delete admin-related information.
     * Admin ile ilgili bilgileri silme izni.
     */
    ADMIN_DELETE("admin:delete"),

    /**
     * Permission to read management-related information.
     * Yönetim ile ilgili bilgileri okuma izni.
     */
    MANAGER_READ("management:read"),

    /**
     * Permission to update management-related information.
     * Yönetim ile ilgili bilgileri güncelleme izni.
     */
    MANAGER_UPDATE("management:update"),

    /**
     * Permission to create management-related information.
     * Yönetim ile ilgili bilgileri oluşturma izni.
     */
    MANAGER_CREATE("management:create"),

    /**
     * Permission to delete management-related information.
     * Yönetim ile ilgili bilgileri silme izni.
     */
    MANAGER_DELETE("management:delete");

    @Getter
    private final String permission;
}
