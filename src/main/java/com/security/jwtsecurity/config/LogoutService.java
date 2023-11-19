package com.security.jwtsecurity.config;

import com.security.jwtsecurity.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

/**
 * LogoutService class implements the LogoutHandler interface.
 * Handles user logout by invalidating the JWT token associated with the user.
 * <p>
 * LogoutService sınıfı LogoutHandler arayüzünü uygular.
 * Kullanıcının çıkışını işler ve kullanıcıyla ilişkilendirilmiş JWT token'ını geçersiz kılar.
 */
@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    /**
     * Handles user logout by invalidating the JWT token associated with the user.
     * Clears the security context after logout.
     * <p>
     * Kullanıcının çıkışını işler ve kullanıcıyla ilişkilendirilmiş JWT token'ını geçersiz kılar.
     * Çıkış sonrasında güvenlik bağlamını temizler.
     *
     * @param request        HttpServletRequest object representing the HTTP request.
     * @param response       HttpServletResponse object representing the HTTP response.
     * @param authentication Authentication object representing the user's authentication.
     */
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }
}
