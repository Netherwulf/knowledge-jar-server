package netherwulf.springframework.knowledgejar.services;

import netherwulf.springframework.knowledgejar.api.v1.model.SubchapterDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.SubchapterListDTO;

public interface SubchapterService {

    SubchapterListDTO getAllSubchaptersByChapterId(Long id);

    SubchapterDTO getByChapterIdAndSubchapterId(Long chapterId, Long subchapterId);

}
