package JavaExam.Coursework2.controller;
import JavaExam.Coursework2.model.Question;
import JavaExam.Coursework2.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/third")
public class ThirdQuestionController {
    private final QuestionService questionService;
    public ThirdQuestionController(@Qualifier("mathQuestionService")QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/add")
    public Question add(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionService.add(question, answer);
    }
    @GetMapping(path = "/remove")
    public Question remove(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionService.remove(new Question(question, answer));
    }
    @GetMapping()
    public Collection<Question> getAll() {
        return questionService.getAll();
    }
}