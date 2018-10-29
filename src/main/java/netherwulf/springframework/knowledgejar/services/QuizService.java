package netherwulf.springframework.knowledgejar.services;

import netherwulf.springframework.knowledgejar.api.v1.model.QuizDTO;

public interface QuizService {
    QuizDTO getByChapterId(Long chapterId);
}
