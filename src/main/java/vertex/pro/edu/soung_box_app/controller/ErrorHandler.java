package vertex.pro.edu.soung_box_app.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vertex.pro.edu.soung_box_app.exception.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserException.class, EntityNotFoundException.class, NoSubscriptionException.class,
            SongAlreadyLikedException.class, TokenExpiredException.class, EntityNotFoundException.class})
    public ResponseEntity<ErrorDto> handlePassError(Exception e, HttpServletRequest request) {
        return ResponseEntity.status(401).body(new ErrorDto(e.getMessage() + " on " + request.getPathInfo()));
    }

}
