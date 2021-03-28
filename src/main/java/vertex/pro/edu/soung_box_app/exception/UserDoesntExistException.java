package vertex.pro.edu.soung_box_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserDoesntExistException extends Exception{
    public UserDoesntExistException(String message) {
        super(message);
    }
}
