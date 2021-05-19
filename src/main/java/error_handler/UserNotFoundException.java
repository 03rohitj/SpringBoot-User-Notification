package error_handler;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("user record with id : "+id+", does not exist");
    }
}
