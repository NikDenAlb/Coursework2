package JavaExam.Coursework2;

import JavaExam.Coursework2.exceptions.NullQuestionIsNotAllowedException;
import JavaExam.Coursework2.exceptions.QuestionAlreadyExistsException;
import JavaExam.Coursework2.model.Question;
import JavaExam.Coursework2.repository.JavaQuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static JavaExam.Coursework2.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionRepositoryTests {
    private JavaQuestionRepository javaQuestionRepository;

    @BeforeEach
    void setUp() {
        javaQuestionRepository = new JavaQuestionRepository();
    }

    @Test
    void getAll() {
        Set<Question> expected = new HashSet<>();
        Assertions.assertIterableEquals(expected, javaQuestionRepository.getAll());

        javaQuestionRepository.add(QUESTION_EXAMPLE);
        expected.add(QUESTION_EXAMPLE2);
        assertFalse(expected.containsAll(javaQuestionRepository.getAll()));
        assertFalse(javaQuestionRepository.getAll().containsAll(expected));

        javaQuestionRepository.add(QUESTION_EXAMPLE2);
        assertTrue(javaQuestionRepository.getAll().containsAll(expected));
        assertFalse(expected.containsAll(javaQuestionRepository.getAll()));

        expected.add(QUESTION_EXAMPLE);
        assertTrue(javaQuestionRepository.getAll().containsAll(expected));
        assertTrue(expected.containsAll(javaQuestionRepository.getAll()));
    }

    @Test
    void addNegativeTest() {
        javaQuestionRepository.add(QUESTION_EXAMPLE);
        assertThrows(QuestionAlreadyExistsException.class, () -> javaQuestionRepository.add(QUESTION_EXAMPLE));
        assertThrows(NullQuestionIsNotAllowedException.class, () -> javaQuestionRepository.add(NULL_QUESTION));
    }

    @Test
    void addTest() {
        assertEquals(EXPECTED_QUESTION1, javaQuestionRepository.add(EXPECTED_QUESTION1));
        assertEquals(EXPECTED_QUESTION2, javaQuestionRepository.add(EXPECTED_QUESTION2));
        assertEquals(EXPECTED_QUESTION3, javaQuestionRepository.add(EXPECTED_QUESTION3));
        assertEquals(EXPECTED_QUESTION4, javaQuestionRepository.add(EXPECTED_QUESTION4));
        assertTrue(EXPECTED_QUESTION_LIST.containsAll(javaQuestionRepository.getAll()));
        assertTrue(javaQuestionRepository.getAll().containsAll(EXPECTED_QUESTION_LIST));
    }

    @Test
    void removeTest() {
        javaQuestionRepository.add(QUESTION_EXAMPLE);
        assertTrue(QUESTION_EXAMPLE_LIST.containsAll(javaQuestionRepository.getAll()));
        assertTrue(javaQuestionRepository.getAll().containsAll(QUESTION_EXAMPLE_LIST));

        assertEquals(QUESTION_EXAMPLE, javaQuestionRepository.remove(QUESTION_EXAMPLE));
        assertTrue(new HashSet<Question>().containsAll(javaQuestionRepository.getAll()));
        assertTrue(javaQuestionRepository.getAll().containsAll(new HashSet<Question>()));

        javaQuestionRepository.add(EXPECTED_QUESTION1);
        javaQuestionRepository.add(EXPECTED_QUESTION2);
        javaQuestionRepository.add(EXPECTED_QUESTION3);
        javaQuestionRepository.add(EXPECTED_QUESTION4);
        assertTrue(EXPECTED_QUESTION_LIST.containsAll(javaQuestionRepository.getAll()));
        assertTrue(javaQuestionRepository.getAll().containsAll(EXPECTED_QUESTION_LIST));

        assertEquals(EXPECTED_QUESTION3, javaQuestionRepository.remove(EXPECTED_QUESTION3));
        assertTrue(EXPECTED_QUESTION_LIST_WITHOUT3.containsAll(javaQuestionRepository.getAll()));
        assertTrue(javaQuestionRepository.getAll().containsAll(EXPECTED_QUESTION_LIST_WITHOUT3));
    }
}