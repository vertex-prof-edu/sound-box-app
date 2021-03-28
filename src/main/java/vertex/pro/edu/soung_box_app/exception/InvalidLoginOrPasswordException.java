package vertex.pro.edu.soung_box_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidLoginOrPasswordException extends Exception{
    public InvalidLoginOrPasswordException(String message) {
        super(message);
    }
}
