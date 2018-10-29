package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.SubchapterMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.SubchapterDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.SubchapterListDTO;
import netherwulf.springframework.knowledgejar.controllers.SubchapterController;
import netherwulf.springframework.knowledgejar.models.Chapter;
import netherwulf.springframework.knowledgejar.models.Subchapter;
import netherwulf.springframework.knowledgejar.repositories.ChapterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubchapterServiceImpl implements SubchapterService {

    private ChapterRepository chapterRepository;
    private SubchapterMapper subchapterMapper;

    public SubchapterServiceImpl(ChapterRepository chapterRepository, SubchapterMapper subchapterMapper) {
        this.chapterRepository = chapterRepository;
        this.subchapterMapper = subchapterMapper;
    }

    @Override
    public SubchapterListDTO getAllSubchaptersByChapterId(Long id) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(id);

        if (!chapterOptional.isPresent()) {
            log.error("Chapter id not found, id: " + id);
        }

        Chapter chapter = chapterOptional.get();

        List<SubchapterDTO> subchapterDTOs = chapter
                .getSubchapters()
                .stream()
                .map(subchapter -> {
                    SubchapterDTO subchapterDTO = subchapterMapper.subchapterToSubchapterDTO(subchapter);
                    subchapterDTO.setSubchapterUrl(SubchapterController.BASE_URL + "/" + chapter.getId() + "/" + "subchapters" + "/" + subchapter.getId());
                    subchapterDTO.setChapterId(subchapter.getChapter().getId());
                    return subchapterDTO;
                })
                .collect(Collectors.toList());

        return new SubchapterListDTO(subchapterDTOs);
    }

    @Override
    public SubchapterDTO getByChapterIdAndSubchapterId(Long chapterId, Long subchapterId) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(chapterId);

        if (!chapterOptional.isPresent()) {
            log.error("Chapter id not found, id: " + chapterId);
        }

        Chapter chapter = chapterOptional.get();

        Optional<Subchapter> subchapterOptional = chapter
                .getSubchapters()
                .stream()
                .filter(subchapter -> subchapter.getId().equals(subchapterId))
                .findFirst();

        if (!subchapterOptional.isPresent()) {
            log.error("Subchapter id not found, id: " + subchapterId);
        }

        Subchapter subchapter = subchapterOptional.get();
        SubchapterDTO subchapterDTO = subchapterMapper.subchapterToSubchapterDTO(subchapter);
        subchapterDTO.setChapterId(subchapter.getChapter().getId());
        subchapterDTO.setSubchapterUrl(SubchapterController.BASE_URL + "/" + chapter.getId() + "/" + "subchapters" + "/" + subchapter.getId());

        return subchapterDTO;
    }
}
