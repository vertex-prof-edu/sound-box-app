package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vertex.pro.edu.soung_box_app.model.token.ConfirmationToken;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query(value="UPDATE ConfirmationToken confirmationToken " + "SET confirmationToken.confirmedAt = ?2 "
            + "WHERE confirmationToken.token = ?1", nativeQuery = true)
    void updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
