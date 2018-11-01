package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.StudentMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.AnswerDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.StudentDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.StudentListDTO;
import netherwulf.springframework.knowledgejar.controllers.StudentController;
import netherwulf.springframework.knowledgejar.exceptions.NotFoundException;
import netherwulf.springframework.knowledgejar.models.Answer;
import netherwulf.springframework.knowledgejar.models.ClosedQuestion;
import netherwulf.springframework.knowledgejar.models.Statement;
import netherwulf.springframework.knowledgejar.models.Student;
import netherwulf.springframework.knowledgejar.repositories.ClosedQuestionRepository;
import netherwulf.springframework.knowledgejar.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService{
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final ClosedQuestionRepository closedQuestionRepository;

    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository, ClosedQuestionRepository closedQuestionRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.closedQuestionRepository = closedQuestionRepository;
    }

    @Override
    public StudentListDTO getAllStudents() {

        List<StudentDTO> studentDTOs =  studentRepository
                .findAll()
                .stream()
                .map(student -> {
                    StudentDTO studentDTO = studentMapper.studentToStudentDTO(student);
                    studentDTO.setStudentUrl(StudentController.BASE_URL + "/" + student.getId());
                    Set<AnswerDTO> answerDTOs = studentDTO.getAnswers();
                    for (AnswerDTO answerDTO : answerDTOs) {
                        Answer answer = studentRepository.findById(studentDTO.getId())
                                .get()
                                .getAnswers()
                                .stream()
                                .filter(answerTemp -> answerTemp.getId().equals(answerDTO.getId()))
                                .findFirst()
                                .get();
                        if (answer.getOpenQuestion() != null) {
                            answerDTO.setOpenQuestionId(answer.getOpenQuestion().getId());
                        }

                        if (answer.getStatement() != null) {
                            answerDTO.setStatementId(answer.getStatement().getId());
                            for (ClosedQuestion closedQuestion : closedQuestionRepository.findAll()) {
                                Set<Statement> statements = closedQuestion.getStatements();
                                Boolean statementFound = statements.stream()
                                        .anyMatch(statementTemp -> statementTemp.getId().equals(answer.getStatement().getId()));
                                if (statementFound) {
                                    answerDTO.setClosedQuestionId(closedQuestion.getId());
                                }
                            }
                        }
                        answerDTO.setIsCorrect(answer.getCorrect());
                        answerDTO.setStudentId(student.getId());
                        answerDTO.setAnswerUrl(StudentController.BASE_URL + "/" + student.getId() + "/" + "answers" + "/" + answer.getId());
                    }
                    studentDTO.setAnswers(answerDTOs);
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
        studentDTO.setStudentUrl(StudentController.BASE_URL + "/" + id);
        Set<AnswerDTO> answerDTOs = studentDTO.getAnswers();
        for (AnswerDTO answerDTO : answerDTOs) {
            Answer answer = studentRepository.findById(studentDTO.getId())
                    .get()
                    .getAnswers()
                    .stream()
                    .filter(answerTemp -> answerTemp.getId().equals(answerDTO.getId()))
                    .findFirst()
                    .get();
            if (answer.getOpenQuestion() != null) {
                answerDTO.setOpenQuestionId(answer.getOpenQuestion().getId());
            }

            if (answer.getStatement() != null) {
                answerDTO.setStatementId(answer.getStatement().getId());
                for (ClosedQuestion closedQuestion : closedQuestionRepository.findAll()) {
                    Set<Statement> statements = closedQuestion.getStatements();
                    Boolean statementFound = statements.stream()
                            .anyMatch(statementTemp -> statementTemp.getId().equals(answer.getStatement().getId()));
                    if (statementFound) {
                        answerDTO.setClosedQuestionId(closedQuestion.getId());
                    }
                }
            }
            answerDTO.setIsCorrect(answer.getCorrect());
            answerDTO.setStudentId(id);
            answerDTO.setAnswerUrl(StudentController.BASE_URL + "/" + id + "/" + "answers" + "/" + answer.getId());
        }
        studentDTO.setAnswers(answerDTOs);
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
