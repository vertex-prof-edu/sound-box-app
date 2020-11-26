package vertex.pro.edu.soung_box_app.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.repository.UserRepository;

import java.util.Optional;

//@Slf4j
@Service
//@RequiredArgsConstructor
public class UserCrudService implements UserRepository {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> find(String id) {
        return userRepository.find(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
