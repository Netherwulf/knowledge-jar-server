package netherwulf.springframework.knowledgejar.repositories;

import netherwulf.springframework.knowledgejar.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
