package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String content;

    @NotBlank
    private Boolean isCorrect;

    @NotBlank
    private String replyDate;

    private String answerUrl;

    @NotBlank
    private Long studentId;

    private Long openQuestionId;
    private Long statementId;
}
