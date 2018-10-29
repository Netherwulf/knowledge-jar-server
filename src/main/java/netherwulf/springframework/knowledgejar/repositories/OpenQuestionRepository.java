package netherwulf.springframework.knowledgejar.repositories;

import netherwulf.springframework.knowledgejar.models.OpenQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenQuestionRepository extends JpaRepository<OpenQuestion, Long> {
}
