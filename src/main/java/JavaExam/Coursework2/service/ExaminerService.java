package JavaExam.Coursework2.service;

import JavaExam.Coursework2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}