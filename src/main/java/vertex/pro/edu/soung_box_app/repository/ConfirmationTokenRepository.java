package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vertex.pro.edu.soung_box_app.entity.token.ConfirmationToken;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    @Query(value = "select * from confirmation_token where token = :token", nativeQuery = true)
    Optional<ConfirmationToken> findByToken(@Param("token") String token);

    @Modifying
    @Transactional
    @Query("UPDATE ConfirmationToken confirmationToken " + "SET confirmationToken.confirmedAt = ?2 "
            + "WHERE confirmationToken.token = ?1")
    void updateConfirmedAt(String token, LocalDateTime confirmedAt);

    @Modifying
    @Query(value = "delete from confirmation_token where token = :token", nativeQuery=true)
    void deleteExpiredToken(@Param("token") String token);
}
