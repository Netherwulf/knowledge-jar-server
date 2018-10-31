package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.ClosedQuestionMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.ClosedQuestionDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.ClosedQuestionListDTO;
import netherwulf.springframework.knowledgejar.controllers.ClosedQuestionController;
import netherwulf.springframework.knowledgejar.exceptions.NotFoundException;
import netherwulf.springframework.knowledgejar.models.ClosedQuestion;
import netherwulf.springframework.knowledgejar.repositories.ClosedQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClosedQuestionServiceImpl implements ClosedQuestionService {

    private final ClosedQuestionMapper closedQuestionMapper;
    private final ClosedQuestionRepository closedQuestionRepository;

    public ClosedQuestionServiceImpl(ClosedQuestionMapper closedQuestionMapper, ClosedQuestionRepository closedQuestionRepository) {
        this.closedQuestionMapper = closedQuestionMapper;
        this.closedQuestionRepository = closedQuestionRepository;
    }

    @Override
    public ClosedQuestionListDTO getAllClosedQuestions() {
        List<ClosedQuestionDTO> closedQuestionDTOs =  closedQuestionRepository
                .findAll()
                .stream()
                .map(closedQuestion -> {
                    ClosedQuestionDTO closedQuestionDTO = closedQuestionMapper.closedQuestionToClosedQuestionDTO(closedQuestion);
                    if (closedQuestion.getQuiz() != null) {
                        closedQuestionDTO.setQuizId(closedQuestion.getQuiz().getId());
                    }
                    if (closedQuestion.getSubchapter() != null) {
                        closedQuestionDTO.setSubchapterId(closedQuestion.getSubchapter().getId());
                    }
                    closedQuestionDTO.setClosedQuestionUrl(ClosedQuestionController.BASE_URL + "/" + closedQuestion.getId());
                    return closedQuestionDTO;
                })
                .collect(Collectors.toList());
        return new ClosedQuestionListDTO(closedQuestionDTOs);
    }

    @Override
    public ClosedQuestionDTO getClosedQuestionById(Long id) {
        Optional<ClosedQuestion> closedQuestionOptional = closedQuestionRepository.findById(id);

        if (!closedQuestionOptional.isPresent()) {
            throw new NotFoundException("ClosedQuestion not found for ID value: " + id.toString());
        }

        ClosedQuestionDTO closedQuestionDTO = closedQuestionMapper.closedQuestionToClosedQuestionDTO(closedQuestionOptional.get());
        closedQuestionDTO.setClosedQuestionUrl(ClosedQuestionController.BASE_URL + "/" + closedQuestionDTO.getId());
        if (closedQuestionOptional.get().getQuiz() != null) {
            closedQuestionDTO.setQuizId(closedQuestionOptional.get().getQuiz().getId());
        }
        if (closedQuestionOptional.get().getSubchapter() != null) {
            closedQuestionDTO.setSubchapterId(closedQuestionOptional.get().getSubchapter().getId());
        }
        return closedQuestionDTO;
    }
}
