package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.StatementMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.StatementDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.StatementListDTO;
import netherwulf.springframework.knowledgejar.controllers.StatementController;
import netherwulf.springframework.knowledgejar.models.ClosedQuestion;
import netherwulf.springframework.knowledgejar.models.Statement;
import netherwulf.springframework.knowledgejar.repositories.ClosedQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatementServiceImpl implements StatementService {
    private final ClosedQuestionRepository closedQuestionRepository;
    private final StatementMapper statementMapper;

    public StatementServiceImpl(ClosedQuestionRepository closedQuestionRepository, StatementMapper statementMapper) {
        this.closedQuestionRepository = closedQuestionRepository;
        this.statementMapper = statementMapper;
    }

    @Override
    public StatementListDTO getAllStatementsByClosedQuestionId(Long id) {
        Optional<ClosedQuestion> closedQuestionOptional = closedQuestionRepository.findById(id);

        if (!closedQuestionOptional.isPresent()) {
            log.error("ClosedQuestion id not found, id: " + id);
        }

        ClosedQuestion closedQuestion = closedQuestionOptional.get();

        List<StatementDTO> statementDTOs = closedQuestion
                .getStatements()
                .stream()
                .map(statement -> {
                    StatementDTO statementDTO = statementMapper.statementToStatementDTO(statement);
                    statementDTO.setStatementUrl(StatementController.BASE_URL + "/" + closedQuestion.getId() + "/" + "statements" + "/" + statement.getId());
                    statementDTO.setClosedQuestionId(statement.getClosedQuestion().getId());
                    return statementDTO;
                })
                .collect(Collectors.toList());

        return new StatementListDTO(statementDTOs);
    }

    @Override
    public StatementDTO getByClosedQuestionIdAndStatementId(Long closedQuestionId, Long statementId) {
        Optional<ClosedQuestion> closedQuestionOptional = closedQuestionRepository.findById(closedQuestionId);

        if (!closedQuestionOptional.isPresent()) {
            log.error("ClosedQuestion id not found, id: " + closedQuestionId);
        }

        ClosedQuestion closedQuestion = closedQuestionOptional.get();

        Optional<Statement> statementOptional = closedQuestion
                .getStatements()
                .stream()
                .filter(statement -> statement.getId().equals(statementId))
                .findFirst();

        if (!statementOptional.isPresent()) {
            log.error("Statement id not found, id: " + statementId);
        }

        Statement statement = statementOptional.get();
        StatementDTO statementDTO = statementMapper.statementToStatementDTO(statement);
        statementDTO.setClosedQuestionId(statement.getClosedQuestion().getId());

        statementDTO.setStatementUrl(StatementController.BASE_URL + "/" + closedQuestion.getId() + "/" + "statements" + "/" + statement.getId());

        return statementDTO;
    }
}
