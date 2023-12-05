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
@Qualifier("thirdQuestionRepository")
public class ThirdQuestionRepository implements QuestionRepository {
    private final Set<Question> questionsBook;

    public ThirdQuestionRepository() {
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
                new Question("3Question1","3Answer1"),
                new Question("3Question2","3Answer2"),
                new Question("3Question3","3Answer3"),
                new Question("3Question4","3Answer4")));
    }
}