package com.security.jwtsecurity.token;

import com.security.jwtsecurity.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Token class represents the entity for authentication tokens in the system.
 * This class is mapped to the "token" table in the database.
 * <p>
 * Token sınıfı, sistemdeki kimlik doğrulama token'larını temsil eden entity'yi ifade eder.
 * Bu sınıf, veritabanındaki "token" tablosuna eşlenir.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    /**
     * The unique identifier for each token.
     * Her bir token için benzersiz tanımlayıcı.
     */
    @Id
    @GeneratedValue
    public Integer id;

    /**
     * The actual token string, unique within the system.
     * Sistem içinde benzersiz olan gerçek token dizesi.
     */
    @Column(unique = true)
    public String token;

    /**
     * The type of the token (e.g., "Bearer").
     * Token'ın türü (ör. "Bearer").
     */
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    /**
     * Flag indicating whether the token is revoked.
     * Token'ın iptal edilip edilmediğini belirtir.
     */
    public boolean revoked;

    /**
     * Flag indicating whether the token has expired.
     * Token'ın süresinin dolup dolmadığını belirten bayrak.
     */
    public boolean expired;

    /**
     * The user associated with the token.
     * Token ile ilişkilendirilmiş kullanıcı.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
