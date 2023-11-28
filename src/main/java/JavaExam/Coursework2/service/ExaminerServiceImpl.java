package JavaExam.Coursework2.service;

import JavaExam.Coursework2.exceptions.AtLeastOneQuestionException;
import JavaExam.Coursework2.exceptions.NotEnoughQuestionsException;
import JavaExam.Coursework2.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private QuestionService examination;
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService examination, QuestionService questionService) {
        this.examination = examination;
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new NotEnoughQuestionsException("You asked too many questions");
        } else if (amount < 1) {
            throw new AtLeastOneQuestionException("You need to ask at least one question");
        } else if (amount == questionService.getAll().size()) {
            return questionService.getAll();
        } else {
            Set<Question> out = new HashSet<>();
            examination = new JavaQuestionService();
            questionService.getAll().forEach(e -> examination.add(e));
            for (int i = 0; i < amount; i++) {
                Question question = examination.getRandomQuestion();
                examination.remove(question);
                out.add(question);
            }
            return out;
        }
    }
}