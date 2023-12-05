package JavaExam.Coursework2.repository;

import JavaExam.Coursework2.exceptions.BlockedMethodException;
import JavaExam.Coursework2.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Primary
@Qualifier("mathQuestionRepository")
public class MathQuestionBlocker implements QuestionRepository {
    @Override
    public Question add(Question question) {
        throw new BlockedMethodException("Call is not allowed");
    }

    @Override
    public Question remove(Question question) {
        throw new BlockedMethodException("Call is not allowed");
    }

    @Override
    public Collection<Question> getAll() {
        throw new BlockedMethodException("Call is not allowed");
    }
}