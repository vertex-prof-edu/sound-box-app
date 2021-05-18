package vertex.pro.edu.soung_box_app.service.registration;

import vertex.pro.edu.soung_box_app.entity.user.UserEntity;

public interface RegistrationService {

    String register(UserEntity userEntity);

    String confirmToken(String token);
}
