package JavaExam.Coursework2.service;

import JavaExam.Coursework2.exceptions.AtLeastOneQuestionException;
import JavaExam.Coursework2.exceptions.BlockedMethodException;
import JavaExam.Coursework2.exceptions.DuplicateQuestionFromDifferentServicesException;
import JavaExam.Coursework2.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final List<QuestionService> questionServices;

    @Autowired
    public ExaminerServiceImpl(List<QuestionService> questionServices) {
        this.questionServices = questionServices;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 1) {
            throw new AtLeastOneQuestionException("1 or more questions pls");
        }

        int[] type = new int[questionServices.size()];
        boolean[] active = new boolean[questionServices.size()];
        int[] questions = new int[questionServices.size()];
        int[] step = new int[questionServices.size()];

        for (int i = 0; i < questionServices.size(); i++) {
            step[i] = 1;
            active[i] = false;
            try {
                active[i] = !questionServices.get(i).getAll().isEmpty();
                type[i] = 0;//List

            } catch (BlockedMethodException exception) {
                type[i] = 1;//Generator
                active[i] = true;
            }

            if (type[i] == 0 && active[i]) {
                questions[i] = questionServices.get(i).getAll().size();
            }
        }

        System.out.println(Arrays.toString(active));
        System.out.println(Arrays.toString(type));
        System.out.println(Arrays.toString(questions));
        System.out.println(Arrays.toString(step));


        int[] outNumbers = new int[questionServices.size()];
        int index = 0;

        while (amount != 0) {
            if (active[index]) {
                switch (type[index]) {
                    case 0:
                        outNumbers[index]++;
                        amount--;
                        if (--questions[index] == 0) {
                            active[index] = false;
                            int localStep = 1;
                            do {
                                step[(index + questionServices.size() - localStep) % questionServices.size()] += step[index];
                            } while (!active[(index + questionServices.size() - localStep++) % questionServices.size()]);
                        }
                        index = (index + step[index]) % questionServices.size();
                        break;
                    case 1:
                        outNumbers[index]++;
                        amount--;
                        index = (index + step[index]) % questionServices.size();
                        break;
                }
            } else {
                index = (index + step[index]) % questionServices.size();
            }
            System.out.println(Arrays.toString(active) + " active");
            System.out.println(Arrays.toString(type) + " type");
            System.out.println(Arrays.toString(outNumbers) + " outNumbers");
            System.out.println(Arrays.toString(step) + " step");
        }

        System.out.println("/////////////////////////");
        System.out.println(Arrays.toString(type));
        System.out.println(Arrays.toString(outNumbers));
        System.out.println(Arrays.toString(step));


        LinkedHashSet<Question> out = new LinkedHashSet<>();
        Random a = new Random();
        Set<Question> examCopySet;
        for (int i = 0; i < outNumbers.length; i++) {
            switch (type[i]) {
                case 0:
                    examCopySet = new HashSet<>(questionServices.get(i).getAll());
                    while (outNumbers[i]-- != 0) {
                        addRandomQuestionToOut(examCopySet, a, out);
                    }
                    break;
                case 1:
                    while (outNumbers[i]-- != 0) {
                        boolean newElement;
                        do {
                            newElement = out.add(questionServices.get(i).getRandomQuestion());
                        }
                        while (!newElement);
                    }
            }
        }
        return out;
    }

    private static void addRandomQuestionToOut(Set<Question> examination, Random a, Set<Question> out) {
        Question question = (Question) examination.toArray()[a.nextInt(examination.size())];
        examination.remove(question);
        if (!out.add(question)) {
            throw new DuplicateQuestionFromDifferentServicesException("Question from different service in not unique");
        }
    }
}