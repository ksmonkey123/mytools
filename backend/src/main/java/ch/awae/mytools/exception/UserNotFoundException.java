package ch.awae.mytools.exception;

public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(long id) {
        super("user", "id", Long.toString(id));
    }

}
