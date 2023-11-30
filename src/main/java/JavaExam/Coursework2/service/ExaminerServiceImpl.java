package JavaExam.Coursework2.service;

import JavaExam.Coursework2.exceptions.AtLeastTwoQuestionException;
import JavaExam.Coursework2.exceptions.NotEnoughQuestionsException;
import JavaExam.Coursework2.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService, @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) {
            throw new NotEnoughQuestionsException("You asked too many questions");
        } else if (amount < 2) {
            throw new AtLeastTwoQuestionException("You need to ask at least 2 question");
        } else if (amount == javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) {
            Set<Question> out = new HashSet<>(javaQuestionService.getAll());
            out.addAll(mathQuestionService.getAll());
            return out;
        } else {
            Random a = new Random();
            Set<Question> out = new HashSet<>();
            Set<Question> examination;
            if (javaQuestionService.getAll().isEmpty()) {
                examination = new HashSet<>(mathQuestionService.getAll());
                for (int i = 0; i < amount - 1; i++) {
                    addRandomQuestionToOut(examination, a, out);
                }
                addRandomQuestionToOutWithoutRemove(examination, a, out);
                return out;
            }
            if (mathQuestionService.getAll().isEmpty()) {
                examination = new HashSet<>(javaQuestionService.getAll());
                for (int i = 0; i < amount - 1; i++) {
                    addRandomQuestionToOut(examination, a, out);
                }
                addRandomQuestionToOutWithoutRemove(examination, a, out);
                return out;
            }

            if (javaQuestionService.getAll().size() == 1) {
                examination = new HashSet<>(mathQuestionService.getAll());
                for (int i = 0; i < amount - 2; i++) {
                    addRandomQuestionToOut(examination, a, out);
                }
                addRandomQuestionToOutWithoutRemove(examination, a, out);
                out.add(javaQuestionService.getRandomQuestion());
                return out;
            }
            if (mathQuestionService.getAll().size() == 1) {
                examination = new HashSet<>(javaQuestionService.getAll());
                for (int i = 0; i < amount - 2; i++) {
                    addRandomQuestionToOut(examination, a, out);
                }
                addRandomQuestionToOutWithoutRemove(examination, a, out);
                out.add(mathQuestionService.getRandomQuestion());
                return out;
            }

            int amountJava;
            examination = new HashSet<>(javaQuestionService.getAll());
            if (amount < examination.size()) {
                amountJava = a.nextInt((amount - 1)) + 1;
            } else {
                amountJava = a.nextInt((examination.size() - 1)) + 1;
            }
            for (int i = 0; i < amountJava-1; i++) {
                addRandomQuestionToOut(examination, a, out);
            }
            addRandomQuestionToOutWithoutRemove(examination, a, out);
            examination = new HashSet<>(mathQuestionService.getAll());
            for (int i = 0; i < (amount - amountJava-1); i++) {
                addRandomQuestionToOut(examination, a, out);
            }
            addRandomQuestionToOutWithoutRemove(examination, a, out);
            return out;
        }
    }

    private static void addRandomQuestionToOut(Set<Question> examination, Random a, Set<Question> out) {
        Question question = (Question) examination.toArray()[a.nextInt(examination.size())];
        examination.remove(question);
        out.add(question);
    }

    private static void addRandomQuestionToOutWithoutRemove(Set<Question> examination, Random a, Set<Question> out) {
        Question question = (Question) examination.toArray()[a.nextInt(examination.size())];
        out.add(question);
    }
}