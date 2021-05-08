package vertex.pro.edu.soung_box_app.controller.request.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class NewUserRequest {
    private final String login;
    private final String email;
    private final String password;
}

