package JavaExam.Coursework2;

import JavaExam.Coursework2.service.ExaminerServiceImpl;
import JavaExam.Coursework2.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static JavaExam.Coursework2.Constants.EXPECTED_QUESTION_LIST;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTests {
    @Mock
    private QuestionService javaQuestionServiceMock;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions() {
        when(javaQuestionServiceMock.getAll()).thenReturn(EXPECTED_QUESTION_LIST);

        assertTrue(EXPECTED_QUESTION_LIST.containsAll(examinerService.getQuestions(EXPECTED_QUESTION_LIST.size())));
        assertTrue(examinerService.getQuestions(EXPECTED_QUESTION_LIST.size()).containsAll(EXPECTED_QUESTION_LIST));

        assertTrue(EXPECTED_QUESTION_LIST.containsAll(examinerService.getQuestions(EXPECTED_QUESTION_LIST.size() - 1)));

        assertTrue(EXPECTED_QUESTION_LIST.containsAll(examinerService.getQuestions((EXPECTED_QUESTION_LIST.size() - 1) / 2)));

        assertTrue(EXPECTED_QUESTION_LIST.containsAll(examinerService.getQuestions(1)));
    }
}