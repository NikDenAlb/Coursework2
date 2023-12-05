package JavaExam.Coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AtLeastOneQuestionException extends IllegalArgumentException {
    public AtLeastOneQuestionException(String message) {
        super(message);
    }
}