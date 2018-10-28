package netherwulf.springframework.knowledgejar.api.v1.mapper;

import netherwulf.springframework.knowledgejar.api.v1.model.ChapterDTO;
import netherwulf.springframework.knowledgejar.models.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChapterMapper {
    ChapterMapper INSTANCE = Mappers.getMapper(ChapterMapper.class);

    ChapterDTO chapterToChapterDTO(Chapter chapter);

    Chapter chapterDTOToChapter(ChapterDTO chapterDTO);
}
