package vertex.pro.edu.soung_box_app.repository;

import org.springframework.stereotype.Repository;
import vertex.pro.edu.soung_box_app.model.user.User;

import java.util.Optional;

@Repository
public interface UserRepository {

    User save(User user);

    Optional<User> find(String id);

    Optional<User> findByUsername(String username);
}
