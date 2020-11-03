package vertex.pro.edu.soung_box_app.service.save;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSaver {

    @Autowired
    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
