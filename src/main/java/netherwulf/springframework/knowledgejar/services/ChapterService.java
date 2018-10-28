package netherwulf.springframework.knowledgejar.services;

import netherwulf.springframework.knowledgejar.api.v1.model.ChapterDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.ChapterListDTO;
import netherwulf.springframework.knowledgejar.models.Chapter;

public interface ChapterService {

    ChapterListDTO getAllChapters();

    ChapterDTO getChapterById(Long id);

    ChapterDTO createNewChapter(ChapterDTO customerDTO);

    ChapterDTO saveChapterByDTO(Long id, ChapterDTO chapterDTO);

    ChapterDTO patchChapter(Long id, ChapterDTO chapterDTO);

    ChapterDTO saveAndReturnDTO(Chapter chapter);

    public void deleteChapterById(Long id);

}
