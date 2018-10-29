package netherwulf.springframework.knowledgejar.services;

import netherwulf.springframework.knowledgejar.api.v1.model.ClosedQuestionDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.ClosedQuestionListDTO;

public interface ClosedQuestionService {

    ClosedQuestionListDTO getAllClosedQuestions();

    ClosedQuestionDTO getClosedQuestionById(Long id);

}
