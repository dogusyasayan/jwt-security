package com.security.jwtsecurity.config;

import com.security.jwtsecurity.auditing.ApplicationAuditAware;
import com.security.jwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Uygulama yapılandırma sınıfı.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UserRepository repository;

  /**
   * Kullanıcı detaylarını sağlayan özel servisi tanımlar.
   *
   * @return Özel kullanıcı detayları servisi.
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return username -> repository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  /**
   * Kimlik doğrulama sağlayıcısını konfigüre eder.
   *
   * @return Kimlik doğrulama sağlayıcısı.
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  /**
   * Denetleyici tarafından kullanılan bilgi sağlayıcısını konfigüre eder.
   *
   * @return Denetleyici tarafından kullanılan bilgi sağlayıcısı.
   */
  @Bean
  public AuditorAware<Integer> auditorAware() {
    return new ApplicationAuditAware();
  }

  /**
   * Kimlik doğrulama yöneticisini konfigüre eder.
   *
   * @param config Kimlik doğrulama yapılandırması.
   * @return Kimlik doğrulama yöneticisi.
   * @throws Exception Kimlik doğrulama yöneticisi oluşturulurken oluşabilecek istisnalar.
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  /**
   * Şifre kodlayıcıyı tanımlar.
   *
   * @return Şifre kodlayıcı.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
