package netherwulf.springframework.knowledgejar.services;

import netherwulf.springframework.knowledgejar.api.v1.model.OpenQuestionDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.OpenQuestionListDTO;

public interface OpenQuestionService {

    OpenQuestionListDTO getAllOpenQuestions();

    OpenQuestionDTO getOpenQuestionById(Long id);

}
