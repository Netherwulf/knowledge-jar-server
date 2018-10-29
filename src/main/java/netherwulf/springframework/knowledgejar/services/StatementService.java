package netherwulf.springframework.knowledgejar.services;

import netherwulf.springframework.knowledgejar.api.v1.model.StatementDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.StatementListDTO;

public interface StatementService {

    StatementListDTO getAllStatementsByClosedQuestionId(Long id);

    StatementDTO getByClosedQuestionIdAndStatementId(Long studentId, Long statementId);

}
