package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.OpenQuestionMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.OpenQuestionDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.OpenQuestionListDTO;
import netherwulf.springframework.knowledgejar.controllers.OpenQuestionController;
import netherwulf.springframework.knowledgejar.exceptions.NotFoundException;
import netherwulf.springframework.knowledgejar.models.OpenQuestion;
import netherwulf.springframework.knowledgejar.repositories.OpenQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OpenQuestionServiceImpl implements OpenQuestionService {

    private final OpenQuestionMapper openQuestionMapper;
    private final OpenQuestionRepository openQuestionRepository;

    public OpenQuestionServiceImpl(OpenQuestionMapper openQuestionMapper, OpenQuestionRepository openQuestionRepository) {
        this.openQuestionMapper = openQuestionMapper;
        this.openQuestionRepository = openQuestionRepository;
    }

    @Override
    public OpenQuestionListDTO getAllOpenQuestions() {
        List<OpenQuestionDTO> openQuestionDTOs =  openQuestionRepository
                .findAll()
                .stream()
                .map(openQuestion -> {
                    OpenQuestionDTO openQuestionDTO = openQuestionMapper.openQuestionToOpenQuestionDTO(openQuestion);
                    openQuestionDTO.setOpenQuestionUrl(OpenQuestionController.BASE_URL + "/" + openQuestion.getId());
                    return openQuestionDTO;
                })
                .collect(Collectors.toList());
        return new OpenQuestionListDTO(openQuestionDTOs);
    }

    @Override
    public OpenQuestionDTO getOpenQuestionById(Long id) {
        Optional<OpenQuestion> openQuestionOptional = openQuestionRepository.findById(id);

        if (!openQuestionOptional.isPresent()) {
            throw new NotFoundException("OpenQuestion not found for ID value: " + id.toString());
        }

        OpenQuestionDTO openQuestionDTO = openQuestionMapper.openQuestionToOpenQuestionDTO(openQuestionOptional.get());
        openQuestionDTO.setOpenQuestionUrl(OpenQuestionController.BASE_URL + "/" + openQuestionDTO.getId());

        return openQuestionDTO;
    }
}
