package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class OpenQuestionDTO {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String content;

    @NotBlank
    @Size(max = 30)
    private String correctAnswer;

    private SubchapterDTO subchapter;
    private Set<AnswerDTO> answers = new HashSet<>();
    private QuizDTO quiz;
}
