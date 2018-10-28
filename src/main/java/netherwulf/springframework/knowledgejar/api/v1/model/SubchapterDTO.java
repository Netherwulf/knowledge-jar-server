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
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String content;

    @NotBlank
    @Size(max = 50)
    private String codeLink;

    private ChapterDTO chapter;
    private OpenQuestionDTO openQuestion;
    private ClosedQuestionDTO closedQuestion;
}
