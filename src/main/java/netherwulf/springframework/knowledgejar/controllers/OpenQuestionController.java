package netherwulf.springframework.knowledgejar.controllers;

import netherwulf.springframework.knowledgejar.api.v1.model.OpenQuestionDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.OpenQuestionListDTO;
import netherwulf.springframework.knowledgejar.services.OpenQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OpenQuestionController.BASE_URL)
public class OpenQuestionController {
    public static final String BASE_URL = "/api/v1/openQuestions";

    private final OpenQuestionService openQuestionService;

    public OpenQuestionController(OpenQuestionService openQuestionService) {
        this.openQuestionService = openQuestionService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OpenQuestionListDTO getAllOpenQuestions() {
        return openQuestionService.getAllOpenQuestions();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OpenQuestionDTO getOpenQuestionById(@PathVariable Long id) {
        return openQuestionService.getOpenQuestionById(id);
    }
}
