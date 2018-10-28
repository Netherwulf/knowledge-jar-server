package netherwulf.springframework.knowledgejar.api.v1.mapper;

import netherwulf.springframework.knowledgejar.api.v1.model.QuizDTO;
import netherwulf.springframework.knowledgejar.models.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuizMapper {
    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    QuizDTO quizToQuizDTO(Quiz quiz);

    Quiz quizDTOToQuiz(QuizDTO quizDTO);
}
