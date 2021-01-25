package vertex.pro.edu.soung_box_app.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.repository.UserRepository;

import java.util.*;

@Service
@Component
@RequiredArgsConstructor
public class UserCrudService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> find(String id) {
        return userRepository.find(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
