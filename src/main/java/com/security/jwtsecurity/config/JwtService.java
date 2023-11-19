package com.security.jwtsecurity.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


/**
 * JWT ile ilgili işlemleri sağlayan servis sınıfı.
 */
@Service
public class JwtService {

    // Uygulama özelliklerinden alınacak JWT ayarları
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    /**
     * Belirtilen JWT token'ından kullanıcı adını çıkarmak için kullanılır.
     *
     * @param token JWT token'ı.
     * @return Token'dan çıkartılan kullanıcı adı.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    /**
     * Belirtilen JWT token'ından bir talebi çıkarmak için kullanılır.
     * Token'ın talepleri üzerine uygulanacak olan ClaimsResolver fonksiyonu.
     *
     * @param token          Talebi çıkarmak için kullanılan JWT token'ı.
     * @param claimsResolver Token'ın taleplerine uygulanacak olan ClaimsResolver fonksiyonu.
     * @param <T>            Talep tipi.
     * @return Tip T'ye sahip çıkartılmış talep.
     */

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Belirtilen kullanıcı detayları ile bir JWT token'ı oluşturmak için kullanılır.
     *
     * @param userDetails Kullanıcı detayları.
     * @return Oluşturulan JWT token'ı.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Belirtilen ek taleplerle bir JWT token'ı oluşturmak için kullanılır.
     *
     * @param extraClaims Ek talepler.
     * @param userDetails Kullanıcı detayları.
     * @return Oluşturulan JWT token'ı.
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Belirtilen ek talepler ve kullanıcı detayları ile bir JWT token'ı oluşturmak için kullanılır.
     *
     * @param extraClaims Ek talepler.
     * @param userDetails Kullanıcı detayları.
     * @param expiration  Token'ın geçerlilik süresi (milisaniye cinsinden).
     * @return Oluşturulan JWT token'ı.
     */
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Belirtilen JWT token'ının geçerliliğini kontrol etmek için kullanılır.
     *
     * @param token       Kontrol edilecek JWT token'ı.
     * @param userDetails Kullanıcı detayları.
     * @return Token geçerliyse true, aksi halde false.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    // JWT token'ının geçerlilik süresini kontrol etmek için kullanılır
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // JWT token'ının süresini çıkartmak için kullanılır
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // JWT token'ından tüm talepleri çıkartmak için kullanılır
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT imzalama anahtarını elde etmek için kullanılır
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
