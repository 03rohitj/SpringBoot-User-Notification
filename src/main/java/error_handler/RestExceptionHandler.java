package error_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class})
    protected ResponseEntity<Object> handleRecordNotFound(RuntimeException ex, WebRequest request){
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message","Requested element not found.");
        responseBody.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}
