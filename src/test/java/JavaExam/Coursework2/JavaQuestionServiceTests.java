package JavaExam.Coursework2;

import JavaExam.Coursework2.exceptions.NoQuestionsException;
import JavaExam.Coursework2.exceptions.NullQuestionIsNotAllowedException;
import JavaExam.Coursework2.exceptions.QuestionAlreadyExistsException;
import JavaExam.Coursework2.model.Question;
import JavaExam.Coursework2.service.JavaQuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static JavaExam.Coursework2.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTests {
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void addNegativeTest() {
        javaQuestionService.add(QUESTION_STRING_EXAMPLE, ANSWER_STRING_EXAMPLE);
        assertThrows(QuestionAlreadyExistsException.class, () -> javaQuestionService.add(QUESTION_STRING_EXAMPLE, ANSWER_STRING_EXAMPLE));
        assertThrows(NullQuestionIsNotAllowedException.class, () -> javaQuestionService.add(null, null));
    }

    @Test
    void getAll() {
        Set<Question> expected = new HashSet<>();
        Assertions.assertIterableEquals(expected, javaQuestionService.getAll());

        javaQuestionService.add(QUESTION_STRING_EXAMPLE, ANSWER_STRING_EXAMPLE);
        expected.add(QUESTION_EXAMPLE2);
        assertFalse(expected.containsAll(javaQuestionService.getAll()));
        assertFalse(javaQuestionService.getAll().containsAll(expected));

        javaQuestionService.add(QUESTION_STRING_EXAMPLE2, ANSWER_STRING_EXAMPLE2);
        assertTrue(javaQuestionService.getAll().containsAll(expected));
        assertFalse(expected.containsAll(javaQuestionService.getAll()));

        expected.add(QUESTION_EXAMPLE);
        assertTrue(javaQuestionService.getAll().containsAll(expected));
        assertTrue(expected.containsAll(javaQuestionService.getAll()));
    }

    @Test
    void addTest() {
        assertEquals(EXPECTED_QUESTION1, javaQuestionService.add(QUESTION_OF_EXPECTED1, ANSWER_OF_EXPECTED1));
        assertEquals(EXPECTED_QUESTION2, javaQuestionService.add(QUESTION_OF_EXPECTED2, ANSWER_OF_EXPECTED2));
        assertEquals(EXPECTED_QUESTION3, javaQuestionService.add(QUESTION_OF_EXPECTED3, ANSWER_OF_EXPECTED3));
        assertEquals(EXPECTED_QUESTION4, javaQuestionService.add(QUESTION_OF_EXPECTED4, ANSWER_OF_EXPECTED4));
        assertTrue(EXPECTED_QUESTION_LIST.containsAll(javaQuestionService.getAll()));
        assertTrue(javaQuestionService.getAll().containsAll(EXPECTED_QUESTION_LIST));
    }

    @Test
    void addQNegativeTest() {
        javaQuestionService.add(QUESTION_EXAMPLE);
        assertThrows(QuestionAlreadyExistsException.class, () -> javaQuestionService.add(QUESTION_EXAMPLE));
        assertThrows(NullQuestionIsNotAllowedException.class, () -> javaQuestionService.add(null, null));
    }

    @Test
    void addQTest() {
        assertEquals(EXPECTED_QUESTION1, javaQuestionService.add(EXPECTED_QUESTION1));
        assertEquals(EXPECTED_QUESTION2, javaQuestionService.add(EXPECTED_QUESTION2));
        assertEquals(EXPECTED_QUESTION3, javaQuestionService.add(EXPECTED_QUESTION3));
        assertEquals(EXPECTED_QUESTION4, javaQuestionService.add(EXPECTED_QUESTION4));
        assertTrue(EXPECTED_QUESTION_LIST.containsAll(javaQuestionService.getAll()));
        assertTrue(javaQuestionService.getAll().containsAll(EXPECTED_QUESTION_LIST));
    }

    @Test
    void removeTest() {
        javaQuestionService.add(QUESTION_EXAMPLE);
        assertTrue(QUESTION_EXAMPLE_LIST.containsAll(javaQuestionService.getAll()));
        assertTrue(javaQuestionService.getAll().containsAll(QUESTION_EXAMPLE_LIST));

        assertEquals(QUESTION_EXAMPLE, javaQuestionService.remove(QUESTION_EXAMPLE));
        assertTrue(new HashSet<Question>().containsAll(javaQuestionService.getAll()));
        assertTrue(javaQuestionService.getAll().containsAll(new HashSet<Question>()));

        javaQuestionService.add(EXPECTED_QUESTION1);
        javaQuestionService.add(EXPECTED_QUESTION2);
        javaQuestionService.add(EXPECTED_QUESTION3);
        javaQuestionService.add(EXPECTED_QUESTION4);
        assertTrue(EXPECTED_QUESTION_LIST.containsAll(javaQuestionService.getAll()));
        assertTrue(javaQuestionService.getAll().containsAll(EXPECTED_QUESTION_LIST));

        assertEquals(EXPECTED_QUESTION3, javaQuestionService.remove(EXPECTED_QUESTION3));
        assertTrue(EXPECTED_QUESTION_LIST_WITHOUT3.containsAll(javaQuestionService.getAll()));
        assertTrue(javaQuestionService.getAll().containsAll(EXPECTED_QUESTION_LIST_WITHOUT3));
    }

    @Test
    void getRandomQuestionTest() {
        assertThrows(NoQuestionsException.class, () -> javaQuestionService.getRandomQuestion());
        javaQuestionService.add(EXPECTED_QUESTION1);
        assertEquals(EXPECTED_QUESTION1, javaQuestionService.getRandomQuestion());
        javaQuestionService.add(EXPECTED_QUESTION2);
        javaQuestionService.add(EXPECTED_QUESTION3);
        javaQuestionService.add(EXPECTED_QUESTION4);
        assertTrue(EXPECTED_QUESTION_LIST.contains(javaQuestionService.getRandomQuestion()));
        assertTrue(EXPECTED_QUESTION_LIST.contains(javaQuestionService.getRandomQuestion()));
        assertTrue(EXPECTED_QUESTION_LIST.contains(javaQuestionService.getRandomQuestion()));
        assertTrue(EXPECTED_QUESTION_LIST.contains(javaQuestionService.getRandomQuestion()));
    }
}