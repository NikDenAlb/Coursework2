package JavaExam.Coursework2;

import JavaExam.Coursework2.exceptions.AtLeastOneQuestionException;
import JavaExam.Coursework2.exceptions.NotEnoughQuestionsException;
import JavaExam.Coursework2.service.ExaminerServiceImpl;
import JavaExam.Coursework2.service.JavaQuestionService;
import JavaExam.Coursework2.service.MathQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static JavaExam.Coursework2.Constants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTests {
    private ExaminerServiceImpl examinerServiceImpl;
    @Mock
    private JavaQuestionService javaQuestionServiceMock;
    @Mock
    private MathQuestionService mathQuestionServiceMock;

    @BeforeEach
    public void initOut() {
        examinerServiceImpl = new ExaminerServiceImpl(javaQuestionServiceMock, mathQuestionServiceMock);
    }

    @Test
    void getQuestions() {
        when(javaQuestionServiceMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        when(mathQuestionServiceMock.getAll()).thenReturn(EXPECTED_MATH_QUESTION_LIST);
        assertTrue(EXPECTED_BOTH_QUESTION_LIST.containsAll(examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size() + EXPECTED_MATH_QUESTION_LIST.size())));
        assertTrue(examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size() + EXPECTED_MATH_QUESTION_LIST.size()).containsAll(EXPECTED_BOTH_QUESTION_LIST));
        assertTrue(EXPECTED_BOTH_QUESTION_LIST.containsAll(examinerServiceImpl.getQuestions(1)));
        assertTrue(EXPECTED_BOTH_QUESTION_LIST.containsAll(examinerServiceImpl.getQuestions((EXPECTED_QUESTION_LIST.size() + EXPECTED_MATH_QUESTION_LIST.size()) / 2)));

        when(javaQuestionServiceMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        when(mathQuestionServiceMock.getAll()).thenReturn(new HashSet<>());
        assertTrue(EXPECTED_QUESTION_LIST.containsAll(examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size())));
        assertTrue(examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size()).containsAll(EXPECTED_QUESTION_LIST));

        when(javaQuestionServiceMock.getAll()).thenReturn(new HashSet<>());
        when(mathQuestionServiceMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        assertTrue(EXPECTED_QUESTION_LIST.containsAll(examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size())));
        assertTrue(examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size()).containsAll(EXPECTED_QUESTION_LIST));

        when(javaQuestionServiceMock.getAll()).thenReturn(QUESTION_EXAMPLE_LIST);
        when(mathQuestionServiceMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        assertTrue(EXPECTED_QUESTION_LIST_WITH_QUESTION_EXAMPLE.containsAll(examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size() + QUESTION_EXAMPLE_LIST.size())));
        assertTrue(examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size() + QUESTION_EXAMPLE_LIST.size()).containsAll(EXPECTED_QUESTION_LIST));

        when(javaQuestionServiceMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        when(mathQuestionServiceMock.getAll()).thenReturn(QUESTION_EXAMPLE_LIST);
        assertTrue(EXPECTED_QUESTION_LIST_WITH_QUESTION_EXAMPLE.containsAll(examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size() + QUESTION_EXAMPLE_LIST.size())));
        assertTrue(examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size() + QUESTION_EXAMPLE_LIST.size()).containsAll(EXPECTED_QUESTION_LIST));
    }

    @Test
    void getQuestionsNegative() {
        when(javaQuestionServiceMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        when(mathQuestionServiceMock.getAll()).thenReturn(EXPECTED_MATH_QUESTION_LIST);

        assertThrows(NotEnoughQuestionsException.class, () -> examinerServiceImpl.getQuestions(EXPECTED_QUESTION_LIST.size() + EXPECTED_MATH_QUESTION_LIST.size() + 1));
        assertThrows(AtLeastOneQuestionException.class, () -> examinerServiceImpl.getQuestions(0));
        assertThrows(AtLeastOneQuestionException.class, () -> examinerServiceImpl.getQuestions(-1));
    }
}