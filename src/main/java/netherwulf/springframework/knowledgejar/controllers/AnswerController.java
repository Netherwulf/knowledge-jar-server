package netherwulf.springframework.knowledgejar.controllers;

import netherwulf.springframework.knowledgejar.api.v1.model.AnswerDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.AnswerListDTO;
import netherwulf.springframework.knowledgejar.services.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AnswerController.BASE_URL)
public class AnswerController {
    public static final String BASE_URL = "/api/v1/students";

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/{id}/answers")
    @ResponseStatus(HttpStatus.OK)
    public AnswerListDTO getAnswersByStudentId(@PathVariable Long id) {
        return answerService.getAllAnswersByStudentId(id);
    }

    @GetMapping("/{studentId}/answers/{answerId}")
    @ResponseStatus(HttpStatus.OK)
    public AnswerDTO getAnswerByStudentIdAndAnswerId(@PathVariable Long studentId, @PathVariable Long answerId) {
        return answerService.getByStudentIdAndAnswerId(studentId, answerId);
    }

    @PostMapping("/{id}/answers")
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerDTO createNewAnswer(@PathVariable Long id, @RequestBody AnswerDTO answerDTO) {
        return answerService.saveAndReturnDTO(id, answerDTO);
    }

    @PutMapping("/{id}/answers")
    @ResponseStatus(HttpStatus.OK)
    public AnswerDTO updateAnswer(@PathVariable Long id, @RequestBody AnswerDTO answerDTO) {
        return answerService.saveAndReturnDTO(id, answerDTO);
    }

    @DeleteMapping("/{studentId}/answers/{answerId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long studentId, @PathVariable Long answerId) {
        answerService.deleteByStudentIdAndAnswerId(studentId, answerId);
    }

}
