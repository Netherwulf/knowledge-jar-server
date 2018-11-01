package netherwulf.springframework.knowledgejar.services;

import netherwulf.springframework.knowledgejar.api.v1.model.AnswerDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.AnswerListDTO;
import netherwulf.springframework.knowledgejar.models.Answer;

public interface AnswerService {

    AnswerListDTO getAllAnswersByStudentId(Long id);

    AnswerDTO getByStudentIdAndAnswerId(Long studentId, Long answerId);

    AnswerDTO saveAndReturnDTO(Long id, AnswerDTO chapter);

    void deleteByStudentIdAndAnswerId(Long studentId, Long answerId);

}
