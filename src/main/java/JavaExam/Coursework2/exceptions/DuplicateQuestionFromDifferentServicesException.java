package JavaExam.Coursework2.exceptions;

public class DuplicateQuestionFromDifferentServicesException extends IllegalArgumentException {
    public DuplicateQuestionFromDifferentServicesException(String message) {
        super(message);
    }
}