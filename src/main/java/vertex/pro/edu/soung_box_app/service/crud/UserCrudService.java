package vertex.pro.edu.soung_box_app.service.crud;

import vertex.pro.edu.soung_box_app.entity.user.UserEntity;

public interface UserCrudService {

    String save(UserEntity userEntity);

    String login(String username, String password);
}
