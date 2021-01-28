package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vertex.pro.edu.soung_box_app.model.user.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value="SELECT * FROM users_with_email WHERE (username = :username)", nativeQuery=true)
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value="SELECT * FROM users_with_email WHERE (email = :email)", nativeQuery=true)
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value="UPDATE User u " + "SET u.enabled = TRUE WHERE u.email = ?1", nativeQuery = true)
    void enableAppUser(String email);
}
