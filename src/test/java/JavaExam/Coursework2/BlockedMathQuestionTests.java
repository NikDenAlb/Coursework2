package JavaExam.Coursework2;

import JavaExam.Coursework2.exceptions.BlockedMethodException;
import JavaExam.Coursework2.repository.MathQuestionBlocker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static JavaExam.Coursework2.Constants.QUESTION_EXAMPLE;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BlockedMathQuestionTests {
    private MathQuestionBlocker mathQuestionRepository;

    @BeforeEach
    void setUp() {
        mathQuestionRepository = new MathQuestionBlocker();
    }

    @Test
    void addQTest() {
        assertThrows(BlockedMethodException.class, () -> mathQuestionRepository.add(QUESTION_EXAMPLE));
    }

    @Test
    void removeTest() {
        assertThrows(BlockedMethodException.class, () -> mathQuestionRepository.remove(QUESTION_EXAMPLE));
    }

    @Test
    void getAll() {
        assertThrows(BlockedMethodException.class, () -> mathQuestionRepository.getAll());
    }
}