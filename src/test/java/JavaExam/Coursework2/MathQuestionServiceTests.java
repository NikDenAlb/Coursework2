package JavaExam.Coursework2;

import JavaExam.Coursework2.exceptions.BlockedMethodException;
import JavaExam.Coursework2.exceptions.NullQuestionIsNotAllowedException;
import JavaExam.Coursework2.repository.MathQuestionRepository;
import JavaExam.Coursework2.service.MathQuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static JavaExam.Coursework2.Constants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTests {
    @Mock
    private MathQuestionRepository mathQuestionRepositoryMock;

    @InjectMocks
    private MathQuestionService mathQuestionService;

    @Test
    void addTest() {
        when(mathQuestionRepositoryMock.add(any())).thenThrow(BlockedMethodException.class);
        assertThrows(BlockedMethodException.class, () -> mathQuestionService.add(QUESTION_STRING_EXAMPLE, ANSWER_STRING_EXAMPLE));
    }

    @Test
    void addNegativeTest() {
        assertThrows(NullQuestionIsNotAllowedException.class, () -> mathQuestionService.add(null, null));
    }

    @Test
    void addQTest() {
        when(mathQuestionRepositoryMock.add(any())).thenThrow(BlockedMethodException.class);
        assertThrows(BlockedMethodException.class, () -> mathQuestionService.add(QUESTION_EXAMPLE));
    }

    @Test
    void removeTest() {
        when(mathQuestionRepositoryMock.remove(QUESTION_EXAMPLE)).thenThrow(BlockedMethodException.class);
        assertThrows(BlockedMethodException.class, () -> mathQuestionService.remove(QUESTION_EXAMPLE));
    }

    @Test
    void getAll() {
        when(mathQuestionRepositoryMock.getAll()).thenThrow(BlockedMethodException.class);
        assertThrows(BlockedMethodException.class, () -> mathQuestionService.getAll());
    }
}