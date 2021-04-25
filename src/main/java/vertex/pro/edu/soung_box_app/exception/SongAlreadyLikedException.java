package vertex.pro.edu.soung_box_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.IM_USED)
public class SongAlreadyLikedException extends Exception{
    public SongAlreadyLikedException(String message) {
        super(message);
    }
}
