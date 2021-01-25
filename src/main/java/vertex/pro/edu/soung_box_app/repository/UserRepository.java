package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vertex.pro.edu.soung_box_app.model.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User save(User user);

    @Query(value="SELECT * FROM users WHERE (id = :id)", nativeQuery=true)
    Optional<User> find(@Param("id") String id);

    @Query(value="SELECT * FROM users WHERE (username = :username)", nativeQuery=true)
    User findByUsername(@Param("username") String username);

}
