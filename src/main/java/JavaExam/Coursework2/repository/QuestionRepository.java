package JavaExam.Coursework2.repository;

import JavaExam.Coursework2.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
}