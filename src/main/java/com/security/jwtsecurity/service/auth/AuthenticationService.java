package com.security.jwtsecurity.service.auth;

import com.security.jwtsecurity.config.JwtService;
import com.security.jwtsecurity.domain.user.User;
import com.security.jwtsecurity.model.request.AuthenticationRequest;
import com.security.jwtsecurity.model.request.RegisterRequest;
import com.security.jwtsecurity.model.response.AuthenticationResponse;
import com.security.jwtsecurity.repository.UserRepository;
import com.security.jwtsecurity.token.Token;
import com.security.jwtsecurity.token.TokenRepository;
import com.security.jwtsecurity.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Yeni bir kullanıcı kaydı oluşturur.
     *
     * @param request Kayıt isteği verilerini içeren RegisterRequest nesnesi.
     * @return Oluşturulan kullanıcı bilgilerini içeren AuthenticationResponse nesnesi.
     */
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    /**
     * Kullanıcının kimlik doğrulama işlemini gerçekleştirir.
     *
     * @param request Kimlik doğrulama isteği verilerini içeren AuthenticationRequest nesnesi.
     * @return Kimlik doğrulama işlemi sonucunda oluşturulan token bilgilerini içeren AuthenticationResponse nesnesi.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    /**
     * Kullanıcının token'ını kaydeder.
     *
     * @param user     Token'ı ilişkilendirilecek kullanıcı.
     * @param jwtToken Kaydedilecek token.
     */
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    /**
     * Kullanıcının tüm geçerli token'larını iptal eder.
     *
     * @param user Token'ları iptal edilecek kullanıcı.
     */
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
