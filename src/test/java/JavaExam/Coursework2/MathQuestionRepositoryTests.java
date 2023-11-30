package JavaExam.Coursework2;

import JavaExam.Coursework2.exceptions.NullQuestionIsNotAllowedException;
import JavaExam.Coursework2.exceptions.QuestionAlreadyExistsException;
import JavaExam.Coursework2.model.Question;
import JavaExam.Coursework2.repository.MathQuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static JavaExam.Coursework2.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

public class MathQuestionRepositoryTests {
    private MathQuestionRepository mathQuestionRepository;

    @BeforeEach
    void setUp() {
        mathQuestionRepository = new MathQuestionRepository();
    }

    @Test
    void getAll() {
        Set<Question> expected = new HashSet<>();
        Assertions.assertIterableEquals(expected, mathQuestionRepository.getAll());

        mathQuestionRepository.add(QUESTION_EXAMPLE);
        expected.add(QUESTION_EXAMPLE2);
        assertFalse(expected.containsAll(mathQuestionRepository.getAll()));
        assertFalse(mathQuestionRepository.getAll().containsAll(expected));

        mathQuestionRepository.add(QUESTION_EXAMPLE2);
        assertTrue(mathQuestionRepository.getAll().containsAll(expected));
        assertFalse(expected.containsAll(mathQuestionRepository.getAll()));

        expected.add(QUESTION_EXAMPLE);
        assertTrue(mathQuestionRepository.getAll().containsAll(expected));
        assertTrue(expected.containsAll(mathQuestionRepository.getAll()));
    }

    @Test
    void addNegativeTest() {
        mathQuestionRepository.add(QUESTION_EXAMPLE);
        assertThrows(QuestionAlreadyExistsException.class, () -> mathQuestionRepository.add(QUESTION_EXAMPLE));
        assertThrows(NullQuestionIsNotAllowedException.class, () -> mathQuestionRepository.add(NULL_QUESTION));
    }

    @Test
    void addTest() {
        assertEquals(EXPECTED_QUESTION1, mathQuestionRepository.add(EXPECTED_QUESTION1));
        assertEquals(EXPECTED_QUESTION2, mathQuestionRepository.add(EXPECTED_QUESTION2));
        assertEquals(EXPECTED_QUESTION3, mathQuestionRepository.add(EXPECTED_QUESTION3));
        assertEquals(EXPECTED_QUESTION4, mathQuestionRepository.add(EXPECTED_QUESTION4));
        assertTrue(EXPECTED_QUESTION_LIST.containsAll(mathQuestionRepository.getAll()));
        assertTrue(mathQuestionRepository.getAll().containsAll(EXPECTED_QUESTION_LIST));
    }

    @Test
    void removeTest() {
        mathQuestionRepository.add(QUESTION_EXAMPLE);
        assertTrue(QUESTION_EXAMPLE_LIST.containsAll(mathQuestionRepository.getAll()));
        assertTrue(mathQuestionRepository.getAll().containsAll(QUESTION_EXAMPLE_LIST));

        assertEquals(QUESTION_EXAMPLE, mathQuestionRepository.remove(QUESTION_EXAMPLE));
        assertTrue(new HashSet<Question>().containsAll(mathQuestionRepository.getAll()));
        assertTrue(mathQuestionRepository.getAll().containsAll(new HashSet<Question>()));

        mathQuestionRepository.add(EXPECTED_QUESTION1);
        mathQuestionRepository.add(EXPECTED_QUESTION2);
        mathQuestionRepository.add(EXPECTED_QUESTION3);
        mathQuestionRepository.add(EXPECTED_QUESTION4);
        assertTrue(EXPECTED_QUESTION_LIST.containsAll(mathQuestionRepository.getAll()));
        assertTrue(mathQuestionRepository.getAll().containsAll(EXPECTED_QUESTION_LIST));

        assertEquals(EXPECTED_QUESTION3, mathQuestionRepository.remove(EXPECTED_QUESTION3));
        assertTrue(EXPECTED_QUESTION_LIST_WITHOUT3.containsAll(mathQuestionRepository.getAll()));
        assertTrue(mathQuestionRepository.getAll().containsAll(EXPECTED_QUESTION_LIST_WITHOUT3));
    }
}