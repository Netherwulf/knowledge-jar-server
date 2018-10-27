package netherwulf.springframework.knowledgejar.repositories;

import netherwulf.springframework.knowledgejar.domain.Chapter;
import org.springframework.data.repository.CrudRepository;

public interface ChapterRepository extends CrudRepository<Chapter, Long> {
}
