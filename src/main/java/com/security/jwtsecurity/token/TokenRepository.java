package com.security.jwtsecurity.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * TokenRepository is an interface for managing Token entities in the database.
 * Extends JpaRepository for basic CRUD operations.
 * <p>
 * TokenRepository, veritabanındaki Token entity'lerini yönetmek için bir arayüzdür.
 * Temel CRUD işlemleri için JpaRepository'i genişletir.
 */
public interface TokenRepository extends JpaRepository<Token, Integer> {

    /**
     * Retrieves a list of valid tokens associated with a user based on the user's ID.
     * Only selects tokens that are not expired or revoked.
     * <p>
     * Kullanıcının ID'sine bağlı olarak geçerli token'ların listesini alır.
     * Yalnızca süresi dolmamış veya iptal edilmemiş token'ları seçer.
     *
     * @param id The ID of the user.
     * @return List of valid tokens associated with the user.
     */
    @Query(value = """
            select t from Token t inner join User u\s
            on t.user.id = u.id\s
            where u.id = :id and (t.expired = false or t.revoked = false)\s
            """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}
