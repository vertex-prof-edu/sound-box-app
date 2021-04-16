package vertex.pro.edu.soung_box_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class UserNotAnArtistException extends Exception{
    public UserNotAnArtistException(String message) {
        super(message);
    }
}
