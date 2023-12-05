package JavaExam.Coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class BlockedMethodException extends RuntimeException {
    public BlockedMethodException(String message) {
        super(message);
    }
}