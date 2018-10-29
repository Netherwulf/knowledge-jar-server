package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ClosedQuestionDTO {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String content;

    private String closedQuestionUrl;

    private Long subchapterId;
    private Long quizId;

    private Set<StatementDTO> statements = new HashSet<>();
}
