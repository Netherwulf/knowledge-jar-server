package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.QuizMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.QuizDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.QuizDTO;
import netherwulf.springframework.knowledgejar.models.Chapter;
import netherwulf.springframework.knowledgejar.repositories.ChapterRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final ChapterRepository chapterRepository;
    private final QuizMapper quizMapper;

    public QuizServiceImpl(ChapterRepository chapterRepository, QuizMapper quizMapper) {
        this.chapterRepository = chapterRepository;
        this.quizMapper = quizMapper;
    }

    @Override
    public QuizDTO getByChapterId(Long chapterId) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(chapterId);

        if (!chapterOptional.isPresent()) {
            log.error("Chapter id not found, id: " + chapterId);
        }

        Chapter chapter = chapterOptional.get();

        QuizDTO quizDTO = quizMapper.quizToQuizDTO(chapter.getQuiz());

        if (quizDTO == null) {
            log.error("Quiz id not found, for chapter id: " + chapterId);
        }

        quizDTO.setChapterId(chapter.getId());

        return quizDTO;
    }
}
