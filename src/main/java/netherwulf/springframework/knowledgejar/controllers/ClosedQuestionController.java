package netherwulf.springframework.knowledgejar.controllers;

import netherwulf.springframework.knowledgejar.api.v1.model.ClosedQuestionDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.ClosedQuestionListDTO;
import netherwulf.springframework.knowledgejar.services.ClosedQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ClosedQuestionController.BASE_URL)
public class ClosedQuestionController {
    public static final String BASE_URL = "/api/v1/closedQuestions";

    private final ClosedQuestionService closedQuestionService;

    public ClosedQuestionController(ClosedQuestionService closedQuestionService) {
        this.closedQuestionService = closedQuestionService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ClosedQuestionListDTO getAllClosedQuestions() {
        return closedQuestionService.getAllClosedQuestions();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClosedQuestionDTO getClosedQuestionById(@PathVariable Long id) {
        return closedQuestionService.getClosedQuestionById(id);
    }
}
