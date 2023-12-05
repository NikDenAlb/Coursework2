package JavaExam.Coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NullQuestionIsNotAllowedException extends IllegalArgumentException {
    public NullQuestionIsNotAllowedException(String message) {
        super(message);
    }
}