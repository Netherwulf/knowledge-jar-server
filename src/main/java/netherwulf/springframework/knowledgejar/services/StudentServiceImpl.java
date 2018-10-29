package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.StudentMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.StudentDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.StudentListDTO;
import netherwulf.springframework.knowledgejar.controllers.StudentController;
import netherwulf.springframework.knowledgejar.exceptions.NotFoundException;
import netherwulf.springframework.knowledgejar.models.Student;
import netherwulf.springframework.knowledgejar.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService{
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentListDTO getAllStudents() {

        List<StudentDTO> studentDTOs =  studentRepository
                .findAll()
                .stream()
                .map(student -> {
                    StudentDTO studentDTO = studentMapper.studentToStudentDTO(student);
                    studentDTO.setStudentUrl(StudentController.BASE_URL + "/" + student.getId());
                    return studentDTO;
                })
                .collect(Collectors.toList());
        return new StudentListDTO(studentDTOs);
    }

    @Override
    public StudentDTO getStudentById(Long id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()) {
            throw new NotFoundException("Student not found for ID value: " + id.toString());
        }

        StudentDTO studentDTO = studentMapper.studentToStudentDTO(studentOptional.get());
        studentDTO.setStudentUrl(StudentController.BASE_URL + "/" + studentDTO.getId());

        return studentDTO;
    }

    @Override
    public StudentDTO createNewStudent(StudentDTO customerDTO) {
        return saveAndReturnDTO(studentMapper.studentDTOToStudent(customerDTO));
    }

    @Override
    public StudentDTO saveStudentByDTO(Long id, StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOToStudent(studentDTO);
        student.setId(id);

        return saveAndReturnDTO(student);
    }

    @Override
    @Transactional
    public StudentDTO saveAndReturnDTO(Student student) {
        Student savedStudent = studentRepository.save(student);

        StudentDTO returnDto = studentMapper.studentToStudentDTO(savedStudent);

        returnDto.setStudentUrl(StudentController.BASE_URL + "/" + savedStudent.getId());

        return returnDto;
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
