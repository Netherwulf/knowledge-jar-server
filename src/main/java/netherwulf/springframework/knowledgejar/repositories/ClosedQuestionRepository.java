package netherwulf.springframework.knowledgejar.repositories;

import netherwulf.springframework.knowledgejar.models.ClosedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClosedQuestionRepository extends JpaRepository<ClosedQuestion, Long> {
}
