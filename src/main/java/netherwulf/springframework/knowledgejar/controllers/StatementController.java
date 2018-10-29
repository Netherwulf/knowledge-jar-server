package netherwulf.springframework.knowledgejar.controllers;

import netherwulf.springframework.knowledgejar.api.v1.model.StatementDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.StatementListDTO;
import netherwulf.springframework.knowledgejar.services.StatementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StatementController.BASE_URL)
public class StatementController {
    public static final String BASE_URL = "/api/v1/closedQuestions";

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/{id}/statements")
    @ResponseStatus(HttpStatus.OK)
    public StatementListDTO getStatementsByClosedQuestionId(@PathVariable Long id) {
        return statementService.getAllStatementsByClosedQuestionId(id);
    }

    @GetMapping("/{closedQuestionId}/statements/{statementId}")
    @ResponseStatus(HttpStatus.OK)
    public StatementDTO getStatementsByClosedQuestionId(@PathVariable Long closedQuestionId, @PathVariable Long statementId) {
        return statementService.getByClosedQuestionIdAndStatementId(closedQuestionId, statementId);
    }
}
