package error_handler;

import java.util.NoSuchElementException;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(Long id) {
        super("notification record with id : "+id+", does not exist");
    }
}
