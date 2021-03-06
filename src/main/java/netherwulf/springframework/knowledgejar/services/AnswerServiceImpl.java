package netherwulf.springframework.knowledgejar.services;

import lombok.extern.slf4j.Slf4j;
import netherwulf.springframework.knowledgejar.api.v1.mapper.AnswerMapper;
import netherwulf.springframework.knowledgejar.api.v1.mapper.ClosedQuestionMapper;
import netherwulf.springframework.knowledgejar.api.v1.mapper.OpenQuestionMapper;
import netherwulf.springframework.knowledgejar.api.v1.mapper.StatementMapper;
import netherwulf.springframework.knowledgejar.api.v1.model.AnswerDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.AnswerListDTO;
import netherwulf.springframework.knowledgejar.controllers.AnswerController;
import netherwulf.springframework.knowledgejar.models.Answer;
import netherwulf.springframework.knowledgejar.models.ClosedQuestion;
import netherwulf.springframework.knowledgejar.models.Statement;
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
    private final StatementMapper statementMapper;
    private final ClosedQuestionMapper closedQuestionMapper;
    private final OpenQuestionMapper openQuestionMapper;

    public AnswerServiceImpl(StudentRepository studentRepository,
                             OpenQuestionRepository openQuestionRepository,
                             ClosedQuestionRepository closedQuestionRepository,
                             AnswerMapper answerMapper,
                             StatementMapper statementMapper,
                             ClosedQuestionMapper closedQuestionMapper,
                             OpenQuestionMapper openQuestionMapper) {
        this.studentRepository = studentRepository;
        this.openQuestionRepository = openQuestionRepository;
        this.closedQuestionRepository = closedQuestionRepository;
        this.answerMapper = answerMapper;
        this.statementMapper = statementMapper;
        this.closedQuestionMapper = closedQuestionMapper;
        this.openQuestionMapper = openQuestionMapper;
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
                    if (answer.getOpenQuestion() != null) {
                        answerDTO.setOpenQuestion(openQuestionMapper.openQuestionToOpenQuestionDTO(answer.getOpenQuestion()));
                    }
                    if (answer.getStatement() != null) {
                        answerDTO.setStatement(statementMapper.statementToStatementDTO(answer.getStatement()));
                    }
                    answerDTO.setIsCorrect(answer.getCorrect());
                    answerDTO.setStudentId(answer.getStudent().getId());
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
        answerDTO.setIsCorrect(answer.getCorrect());
        answerDTO.setStudentId(answer.getStudent().getId());

        if (answer.getOpenQuestion() != null) {
            answerDTO.setOpenQuestion(openQuestionMapper.openQuestionToOpenQuestionDTO(answer.getOpenQuestion()));
        }

        if (answer.getStatement() != null) {
            answerDTO.setStatement(statementMapper.statementToStatementDTO(answer.getStatement()));
        }

        answerDTO.setAnswerUrl(AnswerController.BASE_URL + "/" + student.getId() + "/" + "answers" + "/" + answer.getId());

        return answerDTO;
    }

    @Override
    public AnswerDTO saveAndReturnDTO(Long id, AnswerDTO answerDTO) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()) {
            log.error("Student not found for id: " + id);
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
                if (answerDTO.getOpenQuestion() != null) {
                    answerFound.setOpenQuestion(openQuestionRepository.findById(answerDTO.getOpenQuestion().getId()).get());
                }
                if (answerDTO.getStatement() != null) {
                    answerFound.setStatement(
                            closedQuestionRepository
                                    .findAll()
                                    .stream()
                                    .map(ClosedQuestion::getStatements)
                                    .flatMap(Set::stream)
                                    .filter(statement -> statement.getId().equals(answerDTO.getStatement().getId()))
                                    .findFirst()
                                    .get()
                    );
                    for (ClosedQuestion closedQuestion : closedQuestionRepository.findAll()) {
                        Set<Statement> statements = closedQuestion.getStatements();
                        Boolean statementFound = statements.stream()
                                .anyMatch(statementTemp -> statementTemp.getId().equals(answerDTO.getStatement().getId()));
                        if (statementFound) {
                            answerDTO.setClosedQuestion(closedQuestionMapper.closedQuestionToClosedQuestionDTO(closedQuestion));
                        }
                    }
                }
            } else {
                //add new answer
                Answer answer = answerMapper.answerDTOToAnswer(answerDTO);
                answer.setStudent(student);
                answer.setCorrect(answerDTO.getIsCorrect());
                if (answerDTO.getOpenQuestion() != null) {
                    answer.setOpenQuestion(openQuestionRepository.findById(answerDTO.getOpenQuestion().getId()).get());
                }

                if (answerDTO.getStatement() != null) {
                    answer.setStatement(
                            closedQuestionRepository
                                    .findAll()
                                    .stream()
                                    .map(ClosedQuestion::getStatements)
                                    .flatMap(Set::stream)
                                    .filter(statement -> statement.getId().equals(answerDTO.getStatement().getId()))
                                    .findFirst()
                                    .get()
                    );
                    for (ClosedQuestion closedQuestion : closedQuestionRepository.findAll()) {
                        Set<Statement> statements = closedQuestion.getStatements();
                        Boolean statementFound = statements.stream()
                                .anyMatch(statementTemp -> statementTemp.getId().equals(answer.getStatement().getId()));
                        if (statementFound) {
                            answerDTO.setClosedQuestion(closedQuestionMapper.closedQuestionToClosedQuestionDTO(closedQuestion));
                        }
                    }
                }
                answer.setCorrect(answerDTO.getIsCorrect());
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
                        .filter(answer -> answerDTO.getOpenQuestion() == null ||
                                answer.getOpenQuestion().equals(openQuestionRepository.findById(answerDTO.getOpenQuestion().getId()).get()))
                        .filter(answer -> answerDTO.getStatement() == null ||
                                answer.getStatement()
                                        .equals(closedQuestionRepository
                                            .findAll()
                                            .stream()
                                            .map(ClosedQuestion::getStatements)
                                            .flatMap(Set::stream)
                                            .filter(statement -> statement.getId().equals(answerDTO.getStatement().getId()))
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
                savedAnswerDTO.setOpenQuestion(openQuestionMapper.openQuestionToOpenQuestionDTO(savedAnswer.getOpenQuestion()));
            }

            if (savedAnswer.getStatement() != null) {
                savedAnswerDTO.setStatement(statementMapper.statementToStatementDTO(savedAnswer.getStatement()));
            }

            savedAnswerDTO.setIsCorrect(savedAnswer.getCorrect());
            savedAnswerDTO.setAnswerUrl(AnswerController.BASE_URL + "/" + student.getId() + "/" + "answers" + "/" + savedAnswer.getId());

            return savedAnswerDTO;
        }
    }

    @Override
    public void deleteByStudentIdAndAnswerId(Long studentId, Long answerId) {
        Student student = studentRepository.findById(studentId).get();
        Answer answerToDelete = student.getAnswers()
                .stream()
                .filter(answer -> answer.getId().equals(answerId))
                .findFirst()
                .get();
        answerToDelete.setStudent(null);
        student.getAnswers().removeIf(answer -> answer.getId().equals(answerId));
        studentRepository.save(student);
    }
}
