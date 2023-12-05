package JavaExam.Coursework2;

import JavaExam.Coursework2.exceptions.AtLeastOneQuestionException;
import JavaExam.Coursework2.exceptions.BlockedMethodException;
import JavaExam.Coursework2.exceptions.DuplicateQuestionFromDifferentServicesException;
import JavaExam.Coursework2.service.ExaminerServiceImpl;
import JavaExam.Coursework2.service.JavaQuestionService;
import JavaExam.Coursework2.service.MathQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static JavaExam.Coursework2.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTests {
    private ExaminerServiceImpl examinerServiceImpl;
    @Mock
    private JavaQuestionService javaQuestionServiceMock;
    @Mock
    private MathQuestionService mathQuestionServiceMock;
    @Mock
    private MathQuestionService thirdQuestionServiceMock;

    @BeforeEach
    public void initOut() {
        examinerServiceImpl = new ExaminerServiceImpl(List.of(javaQuestionServiceMock, mathQuestionServiceMock, thirdQuestionServiceMock));
    }

    @Test
    void getQuestions() {
        when(javaQuestionServiceMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        when(mathQuestionServiceMock.getAll()).thenThrow(BlockedMethodException.class);
        when(mathQuestionServiceMock.getRandomQuestion()).thenCallRealMethod();
        when(thirdQuestionServiceMock.getAll()).thenReturn(EXPECTED_MATH_QUESTION_LIST);

        assertTrue(examinerServiceImpl.getQuestions((EXPECTED_QUESTION_LIST.size() + EXPECTED_MATH_QUESTION_LIST.size()) * 2).containsAll(EXPECTED_BOTH_QUESTION_LIST));
        assertEquals((EXPECTED_QUESTION_LIST.size() + EXPECTED_MATH_QUESTION_LIST.size()) * 2, examinerServiceImpl.getQuestions((EXPECTED_QUESTION_LIST.size() + EXPECTED_MATH_QUESTION_LIST.size()) * 2).size());
    }

    @Test
    void getQuestionsNegative() {
        when(javaQuestionServiceMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);
        when(mathQuestionServiceMock.getAll()).thenThrow(BlockedMethodException.class);
        when(mathQuestionServiceMock.getRandomQuestion()).thenCallRealMethod();
        when(thirdQuestionServiceMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST_WITHOUT3);

        assertThrows(DuplicateQuestionFromDifferentServicesException.class, () -> examinerServiceImpl.getQuestions(10));
        assertThrows(AtLeastOneQuestionException.class, () -> examinerServiceImpl.getQuestions(0));
    }
}