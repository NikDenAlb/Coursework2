package JavaExam.Coursework2.service;

import JavaExam.Coursework2.exceptions.NoQuestionsException;
import JavaExam.Coursework2.exceptions.NullQuestionIsNotAllowedException;
import JavaExam.Coursework2.model.Question;
import JavaExam.Coursework2.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
@Qualifier("mathQuestionService")
public class MathQuestionService implements QuestionService {
    private final QuestionRepository javaQuestionRepository;

    public MathQuestionService(@Qualifier("mathQuestionRepository")QuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        if (question == null) {
            throw new NullQuestionIsNotAllowedException("String Question can't be null");
        }
         return javaQuestionRepository.add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return javaQuestionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return javaQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (javaQuestionRepository.getAll().isEmpty()) {
            throw new NoQuestionsException("There is no questions");
        }
        Random a = new Random();
        return (Question) javaQuestionRepository.getAll().toArray()[a.nextInt(javaQuestionRepository.getAll().size())];
    }
}