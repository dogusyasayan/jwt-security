package com.security.jwtsecurity.domain.user;

import com.security.jwtsecurity.domain.enums.user.Role;
import com.security.jwtsecurity.token.Token;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * User class represents the entity for users in the system.
 * Implements the UserDetails interface provided by Spring Security.
 * This class is mapped to the "_user" table in the database.
 * <p>
 * User sınıfı, sistemdeki kullanıcıları temsil eden entity'yi ifade eder.
 * Spring Security tarafından sağlanan UserDetails arayüzünü uygular.
 * Bu sınıf, veritabanındaki "_user" tablosuna eşlenir.
 */
@Entity
@Table(name = "_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    /**
     * The unique identifier for each user.
     * Her bir kullanıcı için benzersiz tanımlayıcı.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * First name of the user.
     * Kullanıcının adı.
     */
    private String firstname;

    /**
     * Last name of the user.
     * Kullanıcının soyadı.
     */
    private String lastname;

    /**
     * Email address of the user, used as the username for authentication.
     * Kullanıcının kimlik doğrulama için kullanılan e-posta adresi.
     */
    private String email;

    /**
     * Password of the user.
     * Kullanıcının şifresi.
     */
    private String password;

    /**
     * The role of the user, stored as a string in the database.
     * Kullanıcının rolü, veritabanında bir dize olarak saklanır.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * List of tokens associated with the user.
     * One-to-many relationship with the Token entity.
     * Kullanıcıyla ilişkilendirilmiş token'ların listesi.
     * Token entity ile birçok-bire çok ilişkisi.
     */
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    /**
     * Returns the authorities (roles) assigned to the user.
     * Implements the getAuthorities() method of the UserDetails interface.
     * Kullanıcıya atanmış yetkileri (rolleri) döndürür.
     * UserDetails arayüzünün getAuthorities() metodunu uygular.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    /**
     * Returns the password of the user.
     * Implements the getPassword() method of the UserDetails interface.
     * Kullanıcının şifresini döndürür.
     * UserDetails arayüzünün getPassword() metodunu uygular.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username (email) of the user.
     * Implements the getUsername() method of the UserDetails interface.
     * Kullanıcının adını (e-posta adresini) döndürür.
     * UserDetails arayüzünün getUsername() metodunu uygular.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired.
     * Implements the isAccountNonExpired() method of the UserDetails interface.
     * Kullanıcının hesabının süresinin dolup dolmadığını belirtir.
     * UserDetails arayüzünün isAccountNonExpired() metodunu uygular.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked.
     * Implements the isAccountNonLocked() method of the UserDetails interface.
     * Kullanıcının hesabının kilitli olup olmadığını belirtir.
     * UserDetails arayüzünün isAccountNonLocked() metodunu uygular.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     * Implements the isCredentialsNonExpired() method of the UserDetails interface.
     * Kullanıcının kimlik bilgilerinin (şifresi) süresinin dolup dolmadığını belirtir.
     * UserDetails arayüzünün isCredentialsNonExpired() metodunu uygular.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled.
     * Implements the isEnabled() method of the UserDetails interface.
     * Kullanıcının etkin olup olmadığını belirtir.
     * UserDetails arayüzünün isEnabled() metodunu uygular.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

