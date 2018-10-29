package netherwulf.springframework.knowledgejar.controllers;

import netherwulf.springframework.knowledgejar.api.v1.model.QuizDTO;
import netherwulf.springframework.knowledgejar.services.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(QuizController.BASE_URL)
public class QuizController {
    public static final String BASE_URL = "/api/v1/chapters";

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}/quizzes")
    @ResponseStatus(HttpStatus.OK)
    public QuizDTO getQuizByChapterId(@PathVariable Long id) {
        return quizService.getByChapterId(id);
    }

}
