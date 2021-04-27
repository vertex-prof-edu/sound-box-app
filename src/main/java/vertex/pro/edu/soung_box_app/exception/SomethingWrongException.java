package vertex.pro.edu.soung_box_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class SomethingWrongException extends Exception{
    public SomethingWrongException(String message) {
        super(message);
    }
}