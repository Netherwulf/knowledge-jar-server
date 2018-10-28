package netherwulf.springframework.knowledgejar.repositories;

import netherwulf.springframework.knowledgejar.models.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
