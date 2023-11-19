package com.security.jwtsecurity.auditing;

import com.security.jwtsecurity.domain.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Bu sınıf, Spring Data JPA tarafından gerçekleştirilen denetleme (auditing) işlemleri için
 * kullanıcı bilincini sağlar. Bu, oluşturulan veya güncellenen varlıkların kim tarafından
 * gerçekleştirildiğini takip etmek için kullanılır.
 */
public class ApplicationAuditAware implements AuditorAware<Integer> {

    /**
     * Şu anda kimin denetlediğini getirir.
     *
     * @return Şu anda kimin denetlediğini içeren bir Optional nesnesi.
     */
    @Override
    public Optional<Integer> getCurrentAuditor() {
        // Güvenlik bağlamını al
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        // Kimlik doğrulama yoksa veya kimlik doğrulama yapılmamışsa veya
        // kimlik doğrulama anonim bir kimlikse boş bir Optional döndür
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }

        // Kimlik doğrulamasını al
        User userPrincipal = (User) authentication.getPrincipal();

        // Kullanıcı kimliğini Optional içinde döndür
        return Optional.ofNullable(userPrincipal.getId());
    }
}
