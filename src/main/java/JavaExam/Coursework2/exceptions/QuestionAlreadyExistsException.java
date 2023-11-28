package JavaExam.Coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuestionAlreadyExistsException extends IllegalArgumentException {
    public QuestionAlreadyExistsException(String message) {
        super(message);
    }
}