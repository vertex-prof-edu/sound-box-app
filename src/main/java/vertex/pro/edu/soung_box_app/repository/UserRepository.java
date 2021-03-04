package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query(value="SELECT * FROM users WHERE username = :username", nativeQuery=true)
    UserEntity findByUsername(@Param("username") String username);

    @Query(value="SELECT * FROM users WHERE email = :email", nativeQuery=true)
    Optional<UserEntity> findByEmail(@Param("email") String email);

//    @Query(value="SELECT * FROM users WHERE username = :username", nativeQuery=true)
//    User findUser(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u " + "SET u.enabled = TRUE WHERE u.username = :username")
    void enableUser(@Param("username") String username);
}
