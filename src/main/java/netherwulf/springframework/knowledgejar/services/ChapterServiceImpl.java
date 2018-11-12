package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.ChapterMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.*;
import netherwulf.springframework.knowledgejar.controllers.ChapterController;
import netherwulf.springframework.knowledgejar.controllers.ClosedQuestionController;
import netherwulf.springframework.knowledgejar.controllers.OpenQuestionController;
import netherwulf.springframework.knowledgejar.exceptions.NotFoundException;
import netherwulf.springframework.knowledgejar.models.*;
import netherwulf.springframework.knowledgejar.repositories.ChapterRepository;
import netherwulf.springframework.knowledgejar.exceptions.ResourceNotFoundException;
import netherwulf.springframework.knowledgejar.repositories.ClosedQuestionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterMapper chapterMapper;
    private final ChapterRepository chapterRepository;
    private final ClosedQuestionRepository closedQuestionRepository;

    public ChapterServiceImpl(ChapterMapper chapterMapper, ChapterRepository chapterRepository, ClosedQuestionRepository closedQuestionRepository) {
        this.chapterMapper = chapterMapper;
        this.chapterRepository = chapterRepository;
        this.closedQuestionRepository = closedQuestionRepository;
    }

    @Override
    public ChapterListDTO getAllChapters() {

        List<ChapterDTO> chapterDTOs =  chapterRepository
                .findAll()
                .stream()
                .map(chapter -> {
                    ChapterDTO chapterDTO = chapterMapper.chapterToChapterDTO(chapter);
                    chapterDTO.setChapterUrl(ChapterController.BASE_URL + "/" + chapter.getId());
                    QuizDTO quizDTO = chapterDTO.getQuiz();
                    quizDTO.setChapterId(chapter.getId());
                    quizDTO.setQuizUrl(ChapterController.BASE_URL + "/" + chapter.getId() + "/" + "quizzes");
                    if (quizDTO.getOpenQuestions() != null) {
                        for (OpenQuestionDTO openQuestionDTO : quizDTO.getOpenQuestions()) {
                            openQuestionDTO.setOpenQuestionUrl(OpenQuestionController.BASE_URL + "/" + openQuestionDTO.getId());
                            openQuestionDTO.setQuizId(quizDTO.getId());
                        }
                    }

                    if (quizDTO.getClosedQuestions() != null) {
                        for (ClosedQuestionDTO closedQuestionDTO : quizDTO.getClosedQuestions()) {
                            closedQuestionDTO.setClosedQuestionUrl(ClosedQuestionController.BASE_URL + "/" + closedQuestionDTO.getId());
                            closedQuestionDTO.setQuizId(quizDTO.getId());

                            for (StatementDTO statementDTO : closedQuestionDTO.getStatements()) {
                                statementDTO.setClosedQuestionId(closedQuestionDTO.getId());
                                statementDTO.setStatementUrl(ClosedQuestionController.BASE_URL + "/" + closedQuestionDTO.getId() + "/statements/" + statementDTO.getId());
                                Statement statement = closedQuestionRepository.findAll()
                                        .stream()
                                        .map(ClosedQuestion::getStatements)
                                        .flatMap(Set::stream)
                                        .filter(statementTemp -> statementTemp.getId().equals(statementDTO.getId()))
                                        .findFirst()
                                        .get();
                                statementDTO.setIsCorrect(statement.getIsCorrect());
                            }
                        }
                    }
                    chapterDTO.setQuiz(quizDTO);

                    List<SubchapterDTO> subchapterDTOs = chapterDTO.getSubchapters();
                    for (SubchapterDTO subchapterDTO : subchapterDTOs) {
                        subchapterDTO.setSubchapterUrl(ChapterController.BASE_URL + "/" + chapter.getId() + "/subchapters/" + subchapterDTO.getId());
                        subchapterDTO.setChapterId(chapterDTO.getId());
                        if (subchapterDTO.getClosedQuestion() != null) {
                            ClosedQuestionDTO closedQuestionDTO = subchapterDTO.getClosedQuestion();
                            closedQuestionDTO.setClosedQuestionUrl(ClosedQuestionController.BASE_URL + "/" + closedQuestionDTO.getId());
                            closedQuestionDTO.setSubchapterId(subchapterDTO.getId());

                            for (StatementDTO statementDTO : closedQuestionDTO.getStatements()) {
                                statementDTO.setClosedQuestionId(closedQuestionDTO.getId());
                                statementDTO.setStatementUrl(ClosedQuestionController.BASE_URL + "/" + closedQuestionDTO.getId() + "/statements/" + statementDTO.getId());
                                Statement statement = closedQuestionRepository.findAll()
                                        .stream()
                                        .map(ClosedQuestion::getStatements)
                                        .flatMap(Set::stream)
                                        .filter(statementTemp -> statementTemp.getId().equals(statementDTO.getId()))
                                        .findFirst()
                                        .get();
                                statementDTO.setIsCorrect(statement.getIsCorrect());
                            }
                        }
                        if (subchapterDTO.getOpenQuestion() != null) {
                            OpenQuestionDTO openQuestionDTO = subchapterDTO.getOpenQuestion();
                            openQuestionDTO.setOpenQuestionUrl(OpenQuestionController.BASE_URL + "/" + openQuestionDTO.getId());
                            openQuestionDTO.setSubchapterId(subchapterDTO.getId());
                        }
                    }
                    subchapterDTOs.sort(Comparator.comparing(SubchapterDTO::getId));
                    chapterDTO.setSubchapters(subchapterDTOs);
                    return chapterDTO;
                })
                .collect(Collectors.toList());
        return new ChapterListDTO(chapterDTOs);
    }

    @Override
    public ChapterDTO getChapterById(Long id) {

        Optional<Chapter> chapterOptional = chapterRepository.findById(id);

        if (!chapterOptional.isPresent()) {
            throw new NotFoundException("Chapter not found for ID value: " + id.toString());
        }

        ChapterDTO chapterDTO = chapterMapper.chapterToChapterDTO(chapterOptional.get());
        chapterDTO.setChapterUrl(ChapterController.BASE_URL + "/" + chapterDTO.getId());

        return chapterDTO;
    }

    @Override
    public ChapterDTO createNewChapter(ChapterDTO customerDTO) {
        return saveAndReturnDTO(chapterMapper.chapterDTOToChapter(customerDTO));
    }

    @Override
    public ChapterDTO saveChapterByDTO(Long id, ChapterDTO chapterDTO) {
        Chapter chapter = chapterMapper.chapterDTOToChapter(chapterDTO);
        chapter.setId(id);

        return saveAndReturnDTO(chapter);
    }

    @Override
    public ChapterDTO patchChapter(Long id, ChapterDTO chapterDTO) {
        return chapterRepository.findById(id)
                .map( customer -> {
                    if(chapterDTO.getName() != null) {
                        customer.setName(chapterDTO.getName());
                    }
                    if(chapterDTO.getDescription() != null) {
                        customer.setDescription(chapterDTO.getDescription());
                    }

                    return saveAndReturnDTO(customer);
                })
                .orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    @Transactional
    public ChapterDTO saveAndReturnDTO(Chapter chapter) {
        Chapter savedChapter = chapterRepository.save(chapter);

        ChapterDTO returnDto = chapterMapper.chapterToChapterDTO(savedChapter);

        returnDto.setChapterUrl(ChapterController.BASE_URL + "/" + savedChapter.getId());

        return returnDto;
    }

    @Override
    public void deleteChapterById(Long id) {
        chapterRepository.deleteById(id);
    }
}
