package netherwulf.springframework.knowledgejar.services;

import netherwulf.springframework.knowledgejar.api.v1.model.StudentDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.StudentListDTO;
import netherwulf.springframework.knowledgejar.models.Student;

public interface StudentService {

    StudentListDTO getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createNewStudent(StudentDTO customerDTO);

    StudentDTO saveStudentByDTO(Long id, StudentDTO chapterDTO);

    StudentDTO saveAndReturnDTO(Student chapter);

    void deleteStudentById(Long id);

}
