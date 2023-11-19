package com.security.jwtsecurity.controller.auth;

import com.security.jwtsecurity.model.request.AuthenticationRequest;
import com.security.jwtsecurity.model.request.RegisterRequest;
import com.security.jwtsecurity.model.response.AuthenticationResponse;
import com.security.jwtsecurity.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kimlik doğrulama ve kayıt işlemlerini yöneten kontrolcü sınıfı.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Yeni bir kullanıcıyı kaydetmek için kullanılan API endpoint'i.
     *
     * @param request Kayıt isteği verilerini içeren RegisterRequest nesnesi.
     * @return Kayıt işlemi sonucunda oluşturulan kullanıcı bilgilerini içeren AuthenticationResponse nesnesi.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * Kullanıcının kimlik doğrulama işlemini gerçekleştirmek için kullanılan API endpoint'i.
     *
     * @param request Kimlik doğrulama isteği verilerini içeren AuthenticationRequest nesnesi.
     * @return Kimlik doğrulama işlemi sonucunda oluşturulan token bilgilerini içeren AuthenticationResponse nesnesi.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
