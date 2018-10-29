package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SubchapterDTO {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private String content;

    @NotBlank
    @Size(max = 100)
    private String codeLink;

    private String subchapterUrl;

    private Long chapterId;

    private OpenQuestionDTO openQuestion;
    private ClosedQuestionDTO closedQuestion;
}
