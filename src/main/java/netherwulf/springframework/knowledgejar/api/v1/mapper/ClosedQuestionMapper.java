package netherwulf.springframework.knowledgejar.api.v1.mapper;

import netherwulf.springframework.knowledgejar.api.v1.model.ClosedQuestionDTO;
import netherwulf.springframework.knowledgejar.models.ClosedQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClosedQuestionMapper {
    ClosedQuestionMapper INSTANCE = Mappers.getMapper(ClosedQuestionMapper.class);

    ClosedQuestionDTO closedQuestionToClosedQuestionDTO(ClosedQuestion closedQuestion);

    ClosedQuestion closedQuestionDTOToClosedQuestion(ClosedQuestionDTO closedQuestionDTO);
}
