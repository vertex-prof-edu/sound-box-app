package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vertex.pro.edu.soung_box_app.entity.UserEntity;
import vertex.pro.edu.soung_box_app.model.user.User;

public interface UserRepository extends JpaRepository<User, String> {

    User save(User user);
}
