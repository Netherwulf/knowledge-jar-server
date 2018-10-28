package netherwulf.springframework.knowledgejar.api.v1.mapper;

import netherwulf.springframework.knowledgejar.api.v1.model.AnswerDTO;
import netherwulf.springframework.knowledgejar.models.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    AnswerDTO answerToAnswerDTO(Answer answer);

    Answer answerDTOToAnswer(AnswerDTO answerDTO);
}
