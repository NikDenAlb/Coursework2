package JavaExam.Coursework2;

import JavaExam.Coursework2.exceptions.NoQuestionsException;
import JavaExam.Coursework2.exceptions.NullQuestionIsNotAllowedException;
import JavaExam.Coursework2.repository.JavaQuestionRepository;
import JavaExam.Coursework2.service.JavaQuestionService;
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
public class JavaQuestionServiceTests {
    @Mock
    private JavaQuestionRepository javaQuestionRepositoryMock;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Test
    void addTest() {
        when(javaQuestionRepositoryMock.add(QUESTION_EXAMPLE)).thenReturn(QUESTION_EXAMPLE);
        javaQuestionService.add(QUESTION_STRING_EXAMPLE, ANSWER_STRING_EXAMPLE);
        assertEquals(QUESTION_EXAMPLE, javaQuestionService.add(QUESTION_STRING_EXAMPLE, ANSWER_STRING_EXAMPLE));
    }

    @Test
    void addNegativeTest() {
        assertThrows(NullQuestionIsNotAllowedException.class, () -> javaQuestionService.add(null, null));
    }

    @Test
    void addQTest() {
        when(javaQuestionRepositoryMock.add(QUESTION_EXAMPLE)).thenReturn(QUESTION_EXAMPLE);
        assertEquals(QUESTION_EXAMPLE, javaQuestionService.add(QUESTION_EXAMPLE));
    }

    @Test
    void removeTest() {
        when(javaQuestionRepositoryMock.remove(QUESTION_EXAMPLE)).thenReturn(QUESTION_EXAMPLE);
        assertEquals(QUESTION_EXAMPLE, javaQuestionService.remove(QUESTION_EXAMPLE));
    }

    @Test
    void getAll() {
        when(javaQuestionRepositoryMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        assertEquals(EXPECTED_QUESTION_LIST, javaQuestionService.getAll());
    }

    @Test
    void getRandomQuestionTest() {
        when(javaQuestionRepositoryMock.getAll()).thenReturn(QUESTION_EXAMPLE_LIST);
        assertEquals(QUESTION_EXAMPLE, javaQuestionService.getRandomQuestion());

        when(javaQuestionRepositoryMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        assertTrue(EXPECTED_QUESTION_LIST.contains(javaQuestionService.getRandomQuestion()));
    }

    @Test
    void getRandomQuestionNegativeTest() {
        when(javaQuestionRepositoryMock.getAll()).thenReturn(new HashSet<>());
        assertThrows(NoQuestionsException.class, () -> javaQuestionService.getRandomQuestion());
    }
}