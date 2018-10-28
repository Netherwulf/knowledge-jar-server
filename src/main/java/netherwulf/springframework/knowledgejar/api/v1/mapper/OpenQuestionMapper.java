package netherwulf.springframework.knowledgejar.api.v1.mapper;

import netherwulf.springframework.knowledgejar.api.v1.model.OpenQuestionDTO;
import netherwulf.springframework.knowledgejar.models.OpenQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpenQuestionMapper {
    OpenQuestionMapper INSTANCE = Mappers.getMapper(OpenQuestionMapper.class);

    OpenQuestionDTO openQuestionToOpenQuestionDTO(OpenQuestion openQuestion);

    OpenQuestion openQuestionDTOToOpenQuestion(OpenQuestionDTO openQuestionDTO);
}
