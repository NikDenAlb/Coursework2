package JavaExam.Coursework2.repository;

import JavaExam.Coursework2.exceptions.NullQuestionIsNotAllowedException;
import JavaExam.Coursework2.exceptions.QuestionAlreadyExistsException;
import JavaExam.Coursework2.exceptions.QuestionIsNotInTheBookException;
import JavaExam.Coursework2.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
@Qualifier("mathQuestionRepository")
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> questionsBook;

    public MathQuestionRepository() {
        questionsBook = new HashSet<>();
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new NullQuestionIsNotAllowedException("Question is null");
        }
        if (questionsBook.add(question)) {
            return question;
        } else {
            throw new QuestionAlreadyExistsException("Question already exists in the questionsBook");
        }
    }

    @Override
    public Question remove(Question question) {
        if (questionsBook.remove(question)) {
            return question;
        } else {
            throw new QuestionIsNotInTheBookException("Question doesn't exists in the questionsBook");
        }
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questionsBook);
    }
    @PostConstruct
    void init() {
        questionsBook.addAll(Set.of(
                new Question("MQuestion1","MAnswer1"),
                new Question("MQuestion2","MAnswer2"),
                new Question("MQuestion3","MAnswer3"),
                new Question("MQuestion4","MAnswer4")));
    }
}