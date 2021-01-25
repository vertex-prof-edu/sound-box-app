package vertex.pro.edu.soung_box_app.exception;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String message, Throwable userAlreadyCreated) {
        super(message, userAlreadyCreated);
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
