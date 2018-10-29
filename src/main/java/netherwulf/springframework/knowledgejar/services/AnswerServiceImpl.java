package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.AnswerMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.AnswerDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.AnswerListDTO;
import netherwulf.springframework.knowledgejar.controllers.AnswerController;
import netherwulf.springframework.knowledgejar.models.Answer;
import netherwulf.springframework.knowledgejar.models.ClosedQuestion;
import netherwulf.springframework.knowledgejar.models.Student;
import netherwulf.springframework.knowledgejar.repositories.ClosedQuestionRepository;
import netherwulf.springframework.knowledgejar.repositories.OpenQuestionRepository;
import netherwulf.springframework.knowledgejar.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AnswerServiceImpl implements AnswerService {

    private final StudentRepository studentRepository;
    private final OpenQuestionRepository openQuestionRepository;
    private final ClosedQuestionRepository closedQuestionRepository;
    private final AnswerMapper answerMapper;

    public AnswerServiceImpl(StudentRepository studentRepository,
                             OpenQuestionRepository openQuestionRepository,
                             ClosedQuestionRepository closedQuestionRepository,
                             AnswerMapper answerMapper) {
        this.studentRepository = studentRepository;
        this.openQuestionRepository = openQuestionRepository;
        this.closedQuestionRepository = closedQuestionRepository;
        this.answerMapper = answerMapper;
    }

    @Override
    public AnswerListDTO getAllAnswersByStudentId(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()) {
            log.error("Student id not found, id: " + id);
        }

        Student student = studentOptional.get();

        List<AnswerDTO> answerDTOs = student
                .getAnswers()
                .stream()
                .map(answer -> {
                    AnswerDTO answerDTO = answerMapper.answerToAnswerDTO(answer);
                    answerDTO.setAnswerUrl(AnswerController.BASE_URL + "/" + student.getId() + "/" + "answers" + "/" + answer.getId());
                    return answerDTO;
                })
                .collect(Collectors.toList());

        return new AnswerListDTO(answerDTOs);
    }

    @Override
    public AnswerDTO getByStudentIdAndAnswerId(Long studentId, Long answerId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (!studentOptional.isPresent()) {
            log.error("Student id not found, id: " + studentId);
        }

        Student student = studentOptional.get();

        Optional<Answer> answerOptional = student
                .getAnswers()
                .stream()
                .filter(answer -> answer.getId().equals(answerId))
                .findFirst();

        if (!answerOptional.isPresent()) {
            log.error("Answer id not found, id: " + answerId);
        }

        Answer answer = answerOptional.get();
        AnswerDTO answerDTO = answerMapper.answerToAnswerDTO(answer);
        answerDTO.setStudentId(answer.getStudent().getId());

        if (answer.getOpenQuestion() != null) {
            answerDTO.setOpenQuestionId(answer.getOpenQuestion().getId());
        }

        if (answer.getStatement() != null) {
            answerDTO.setStatementId(answer.getStatement().getId());
        }

        answerDTO.setAnswerUrl(AnswerController.BASE_URL + "/" + student.getId() + "/" + "answers" + "/" + answer.getId());

        return answerDTO;
    }

    @Override
    public AnswerDTO saveAndReturnDTO(AnswerDTO answerDTO) {
        Optional<Student> studentOptional = studentRepository.findById(answerDTO.getStudentId());

        if (!studentOptional.isPresent()) {
            log.error("Student not found for id: " + answerDTO.getStudentId());
            return new AnswerDTO();
        } else {
            Student student = studentOptional.get();

            Optional<Answer> answerOptional = student
                    .getAnswers()
                    .stream()
                    .filter(answer -> answer.getId().equals(answerDTO.getId()))
                    .findFirst();

            if (answerOptional.isPresent()) {
                //update existing answer
                Answer answerFound = answerOptional.get();
                answerFound.setContent(answerDTO.getContent());
                answerFound.setCorrect(answerDTO.getIsCorrect());
                answerFound.setReplyDate(answerDTO.getReplyDate());
                answerFound.setStudent(student);
                if (answerDTO.getOpenQuestionId() != null) {
                    answerFound.setOpenQuestion(openQuestionRepository.findById(answerDTO.getOpenQuestionId()).get());
                }
                if (answerDTO.getStatementId() != null) {
                    answerFound.setStatement(
                            closedQuestionRepository
                                    .findAll()
                                    .stream()
                                    .map(ClosedQuestion::getStatements)
                                    .flatMap(Set::stream)
                                    .filter(statement -> statement.getId().equals(answerDTO.getStatementId()))
                                    .findFirst()
                                    .get()
                    );
                }
            } else {
                //add new answer
                Answer answer = answerMapper.answerDTOToAnswer(answerDTO);
                answer.setStudent(student);
                student.addAnswer(answer);
            }

            Student savedStudent = studentRepository.save(student);

            Optional<Answer> savedAnswerOptional = savedStudent
                    .getAnswers()
                    .stream()
                    .filter(answer -> answer.getId().equals(answerDTO.getId()))
                    .findFirst();

            if (!savedAnswerOptional.isPresent()) {
                savedAnswerOptional = savedStudent
                        .getAnswers()
                        .stream()
                        .filter(answer -> answer.getContent().equals(answerDTO.getContent()))
                        .filter(answer -> answer.getCorrect().equals(answerDTO.getIsCorrect()))
                        .filter(answer -> answer.getReplyDate().equals(answerDTO.getReplyDate()))
                        .filter(answer -> answer.getStudent().equals(student))
                        .filter(answer -> answer.getOpenQuestion().equals(openQuestionRepository.findById(answerDTO.getOpenQuestionId()).get()))
                        .filter(answer -> answer.getStatement()
                                .equals(closedQuestionRepository
                                        .findAll()
                                        .stream()
                                        .map(ClosedQuestion::getStatements)
                                        .flatMap(Set::stream)
                                        .filter(statement -> statement.getId().equals(answerDTO.getStatementId()))
                                        .findFirst()
                                        .get()
                                )
                        )
                        .findFirst();
            }

            Answer savedAnswer = savedAnswerOptional.get();
            AnswerDTO savedAnswerDTO = answerMapper.answerToAnswerDTO(savedAnswer);
            savedAnswerDTO.setStudentId(savedAnswer.getStudent().getId());

            if (savedAnswer.getOpenQuestion() != null) {
                savedAnswerDTO.setOpenQuestionId(savedAnswer.getOpenQuestion().getId());
            }

            if (savedAnswer.getStatement() != null) {
                savedAnswerDTO.setStatementId(savedAnswer.getStatement().getId());
            }

            answerDTO.setAnswerUrl(AnswerController.BASE_URL + "/" + student.getId() + "/" + "answers" + "/" + savedAnswer.getId());

            return savedAnswerDTO;
        }
    }
}