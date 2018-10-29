package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.ChapterMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.ChapterDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.ChapterListDTO;
import netherwulf.springframework.knowledgejar.controllers.ChapterController;
import netherwulf.springframework.knowledgejar.exceptions.NotFoundException;
import netherwulf.springframework.knowledgejar.models.Chapter;
import netherwulf.springframework.knowledgejar.repositories.ChapterRepository;
import netherwulf.springframework.knowledgejar.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterMapper chapterMapper;
    private final ChapterRepository chapterRepository;

    public ChapterServiceImpl(ChapterMapper chapterMapper, ChapterRepository chapterRepository) {
        this.chapterMapper = chapterMapper;
        this.chapterRepository = chapterRepository;
    }

    @Override
    public ChapterListDTO getAllChapters() {

        List<ChapterDTO> chapterDTOs =  chapterRepository
                .findAll()
                .stream()
                .map(chapter -> {
                    ChapterDTO chapterDTO = chapterMapper.chapterToChapterDTO(chapter);
                    chapterDTO.setChapterUrl(ChapterController.BASE_URL + "/" + chapter.getId());
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
