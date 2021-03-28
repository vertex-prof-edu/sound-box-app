package vertex.pro.edu.soung_box_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
public class TokenExpiredException extends Exception{
    public TokenExpiredException(String message) {
        super(message);
    }
}
