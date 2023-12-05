package JavaExam.Coursework2.service;

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

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository javaQuestionRepository) {
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
        Random a = new Random();
        int x;
        int y;

        switch (a.nextInt(4)) {
            case 0:
                x = a.nextInt(100);
                y = a.nextInt(100);
                return new Question(x + " + " + y + " =?", Integer.toString(x + y));
            case 1:
                x = a.nextInt(100);
                y = a.nextInt(100);
                return new Question(x + " - " + y + " =?", Integer.toString(x - y));
            case 2:
                x = a.nextInt(100);
                y = a.nextInt(100);
                return new Question(x + " * " + y + " =?", Integer.toString(x * y));
            case 3:
                x = a.nextInt(100);
                y = 1+ a.nextInt(99);
                return new Question("(integer) x" + " / " + y + " =?", Integer.toString(x / y));
        }
        throw new RuntimeException("Incorrect work of Random.nextInt");
    }
}