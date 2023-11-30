package JavaExam.Coursework2;

import JavaExam.Coursework2.exceptions.NoQuestionsException;
import JavaExam.Coursework2.exceptions.NullQuestionIsNotAllowedException;
import JavaExam.Coursework2.repository.MathQuestionRepository;
import JavaExam.Coursework2.service.MathQuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static JavaExam.Coursework2.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTests {
    @Mock
    private MathQuestionRepository mathQuestionRepositoryMock;

    @InjectMocks
    private MathQuestionService mathQuestionService;

    @Test
    void addTest() {
        when(mathQuestionRepositoryMock.add(QUESTION_EXAMPLE)).thenReturn(QUESTION_EXAMPLE);
        mathQuestionService.add(QUESTION_STRING_EXAMPLE, ANSWER_STRING_EXAMPLE);
        assertEquals(QUESTION_EXAMPLE, mathQuestionService.add(QUESTION_STRING_EXAMPLE, ANSWER_STRING_EXAMPLE));
    }

    @Test
    void addNegativeTest() {
        assertThrows(NullQuestionIsNotAllowedException.class, () -> mathQuestionService.add(null, null));
    }

    @Test
    void addQTest() {
        when(mathQuestionRepositoryMock.add(QUESTION_EXAMPLE)).thenReturn(QUESTION_EXAMPLE);
        assertEquals(QUESTION_EXAMPLE, mathQuestionService.add(QUESTION_EXAMPLE));
    }

    @Test
    void removeTest() {
        when(mathQuestionRepositoryMock.remove(QUESTION_EXAMPLE)).thenReturn(QUESTION_EXAMPLE);
        assertEquals(QUESTION_EXAMPLE, mathQuestionService.remove(QUESTION_EXAMPLE));
    }

    @Test
    void getAll() {
        when(mathQuestionRepositoryMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        assertEquals(EXPECTED_QUESTION_LIST, mathQuestionService.getAll());
    }

    @Test
    void getRandomQuestionTest() {
        when(mathQuestionRepositoryMock.getAll()).thenReturn(QUESTION_EXAMPLE_LIST);
        assertEquals(QUESTION_EXAMPLE, mathQuestionService.getRandomQuestion());

        when(mathQuestionRepositoryMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        assertTrue(EXPECTED_QUESTION_LIST.contains(mathQuestionService.getRandomQuestion()));
    }

    @Test
    void getRandomQuestionNegativeTest() {
        when(mathQuestionRepositoryMock.getAll()).thenReturn(new HashSet<>());
        assertThrows(NoQuestionsException.class, () -> mathQuestionService.getRandomQuestion());
    }
}