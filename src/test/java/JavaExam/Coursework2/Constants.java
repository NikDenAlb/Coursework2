package JavaExam.Coursework2;

import JavaExam.Coursework2.model.Question;

import java.util.Set;

public class Constants {
    public static final String QUESTION_STRING_EXAMPLE = "QuestionEx";
    public static final String ANSWER_STRING_EXAMPLE = "AnswerEx";
    public static final String QUESTION_STRING_EXAMPLE2 = "QuestionEx2";
    public static final String ANSWER_STRING_EXAMPLE2 = "AnswerEx2";
    public static Question QUESTION_EXAMPLE = new Question(QUESTION_STRING_EXAMPLE, ANSWER_STRING_EXAMPLE);
    public static Question QUESTION_EXAMPLE2 = new Question(QUESTION_STRING_EXAMPLE2, ANSWER_STRING_EXAMPLE2);

    public static final Set<Question> QUESTION_EXAMPLE_LIST = Set.of(
            QUESTION_EXAMPLE);

    public static final String QUESTION_OF_EXPECTED1 = "Question1";
    public static final String QUESTION_OF_EXPECTED2 = "Question2";
    public static final String QUESTION_OF_EXPECTED3 = "Question3";
    public static final String QUESTION_OF_EXPECTED4 = "Question4";
    public static final String ANSWER_OF_EXPECTED1 = "Answer1";
    public static final String ANSWER_OF_EXPECTED2 = "Answer2";
    public static final String ANSWER_OF_EXPECTED3 = "Answer3";
    public static final String ANSWER_OF_EXPECTED4 = "Answer4";
    public static final Question EXPECTED_QUESTION1 = new Question(QUESTION_OF_EXPECTED1, ANSWER_OF_EXPECTED1);
    public static final Question EXPECTED_QUESTION2 = new Question(QUESTION_OF_EXPECTED2, ANSWER_OF_EXPECTED2);
    public static final Question EXPECTED_QUESTION3 = new Question(QUESTION_OF_EXPECTED3, ANSWER_OF_EXPECTED3);
    public static final Question EXPECTED_QUESTION4 = new Question(QUESTION_OF_EXPECTED4, ANSWER_OF_EXPECTED4);
    public static final Set<Question> EXPECTED_QUESTION_LIST = Set.of(
            EXPECTED_QUESTION1,
            EXPECTED_QUESTION2,
            EXPECTED_QUESTION3,
            EXPECTED_QUESTION4);
    public static final Set<Question> EXPECTED_QUESTION_LIST_WITHOUT3 = Set.of(
            EXPECTED_QUESTION1,
            EXPECTED_QUESTION2,
            EXPECTED_QUESTION4);
    public static final String MATH_QUESTION_OF_EXPECTED1 = "MQuestion1";
    public static final String MATH_QUESTION_OF_EXPECTED2 = "MQuestion2";
    public static final String MATH_QUESTION_OF_EXPECTED3 = "MQuestion3";
    public static final String MATH_QUESTION_OF_EXPECTED4 = "MQuestion4";
    public static final String MATH_ANSWER_OF_EXPECTED1 = "MAnswer1";
    public static final String MATH_ANSWER_OF_EXPECTED2 = "MAnswer2";
    public static final String MATH_ANSWER_OF_EXPECTED3 = "MAnswer3";
    public static final String MATH_ANSWER_OF_EXPECTED4 = "MAnswer4";
    public static final Question MATH_EXPECTED_QUESTION1 = new Question(MATH_QUESTION_OF_EXPECTED1, MATH_ANSWER_OF_EXPECTED1);
    public static final Question MATH_EXPECTED_QUESTION2 = new Question(MATH_QUESTION_OF_EXPECTED2, MATH_ANSWER_OF_EXPECTED2);
    public static final Question MATH_EXPECTED_QUESTION3 = new Question(MATH_QUESTION_OF_EXPECTED3, MATH_ANSWER_OF_EXPECTED3);
    public static final Question MATH_EXPECTED_QUESTION4 = new Question(MATH_QUESTION_OF_EXPECTED4, MATH_ANSWER_OF_EXPECTED4);
    public static final Set<Question> EXPECTED_MATH_QUESTION_LIST = Set.of(
            MATH_EXPECTED_QUESTION1,
            MATH_EXPECTED_QUESTION2,
            MATH_EXPECTED_QUESTION3,
            MATH_EXPECTED_QUESTION4);
    public static final Set<Question> EXPECTED_BOTH_QUESTION_LIST = Set.of(
            EXPECTED_QUESTION1,
            EXPECTED_QUESTION2,
            EXPECTED_QUESTION3,
            EXPECTED_QUESTION4,
            MATH_EXPECTED_QUESTION1,
            MATH_EXPECTED_QUESTION2,
            MATH_EXPECTED_QUESTION3,
            MATH_EXPECTED_QUESTION4);
    public static final Set<Question> EXPECTED_QUESTION_LIST_WITH_QUESTION_EXAMPLE = Set.of(
            EXPECTED_QUESTION1,
            EXPECTED_QUESTION2,
            EXPECTED_QUESTION3,
            QUESTION_EXAMPLE,
            EXPECTED_QUESTION4);
    public static Question NULL_QUESTION = null;
}